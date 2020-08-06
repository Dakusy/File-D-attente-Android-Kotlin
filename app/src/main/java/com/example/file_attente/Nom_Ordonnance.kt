package com.example.file_attente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_nom__ordonnance.*


class Nom_Ordonnance : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nom__ordonnance)


        submit_Ordo.setOnClickListener {
            if (Name_Ordo.text.toString().isNotEmpty()) {
                val nom = Name_Ordo.text.toString()
                Number += 1

                val ref = database.getReference("Queue B")
                val ClientID = ref.push().key
                val Client = Client(id = ClientID!!,name = nom, number = Number)
                ref.child(ClientID).setValue(Client).addOnCompleteListener {
                    Toast.makeText(this,"Push in Database Successful",Toast.LENGTH_LONG).show()
                }

                val intent = Intent(this@Nom_Ordonnance, Show_Ordo_Nom::class.java)
                intent.putExtra("Name", nom)
                intent.putExtra("Number", Number)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }

        }
    }
    public fun Back(v: View?) {
            //pour retourner a l’activite principale il suffit seulement d’appler la methode finish() qui vas tuer cette activite

            finish();
        }

}