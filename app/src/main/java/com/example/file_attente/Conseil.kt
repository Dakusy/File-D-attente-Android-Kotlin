package com.example.file_attente

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64;
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_conseil.*
import java.io.ByteArrayOutputStream


class Conseil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conseil)

        Conseil_picture.setOnClickListener {
            //Permet de vérifier sur l'application est utilisé sur un système Android inférieur ou supérieur à Marshmallow
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    //les permissions n'étaient pas accepter
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    //demande des permissions
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    openCamera()
                }
            }
            else{
                //Si le système inférieur à marshmallow pas besoin de vérification des permissions
                openCamera()
            }
        }
    }

    //fonction utilisé sur un bouton permettant le retour en arrière et la fin de la tâche en cours si erreur du client
    public fun Back(v: View?) {
        finish();
    }

    //Redirection vers l'activité Nom_Conseil qui est utilisé si la RGPD n'est pas validé
    fun nom_conseil(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Nom_Conseil::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }

    //Fonction permettant l'activation de la camera pour prendre la photo et l'enregistre en URI
    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)

    }

    //Fonction permettant de vérifier si les permissions requises pour la Camera on été accepter ou pas
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //Fonction appeler quand une personne accepte ou refuse des permissions
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission accepter
                    openCamera()
                }
                else{
                    //si permission refusé
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Après prise de la photo vérifie si tout est bon alors execute le code dans le If qui permet de compresser l'image en bitmap
    //et d'enregistrer les données sur FireBase puis effectue une redirection sur Show_Picture_Ordo pour imprimer.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(
                this.contentResolver,
                Uri.parse(image_uri.toString())
            )
            Number += 1
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            /*val image: String = Base64.encodeToString(byteArray, Base64.DEFAULT)*/
            val ref = database.getReference("Queue C")
            val ClientID = ref.push().key
            val Client = Client_Image(id = ClientID!!, Image = bitmap, number = Number)
            ref.child(ClientID).setValue(Client).addOnCompleteListener {
                Toast.makeText(this,"Push in Database Successful",Toast.LENGTH_LONG).show()
            }

            val intent = Intent(this, Show_Picture_Conseil::class.java)
            intent.putExtra("Number", Number)
            startActivity(intent)
        }
    }
}