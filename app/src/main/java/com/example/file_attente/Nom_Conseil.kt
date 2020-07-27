package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nom__conseil.*

class Nom_Conseil : AppCompatActivity() {

     val context = this
     var db = DataBaseHandler(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nom__conseil)




        submit_Ordo.setOnClickListener {
            if (Name_Ordo.text.toString().isNotEmpty()) {
                var user = User(Name_Ordo.text.toString())
                db.insertData(user)
                val name = Name_Ordo.text.toString()
                Number += 1
                //intent to start activity
                val intent = Intent(this@Nom_Conseil, Show_Conseil_Nom::class.java)
                intent.putExtra("Name", name)
                intent.putExtra("Number", Number)
                startActivity(intent)
            } else {
                Toast.makeText(context,"Please Fill All Data's",Toast.LENGTH_SHORT).show()
            }

        }
    }





       /* submit.setOnClickListener {
            if (Name.text.toString().isNotEmpty()) {
                var user = User(Name.text.toString())
                db.insertData(user)
            } else {
                Toast.makeText(context,"Please Fill All Data's",Toast.LENGTH_SHORT).show()
            }

        }*/


    public fun Back(v: View?) {
        //pour retourner a l’activite principale il suffit seulement d’appler la methode finish() qui vas tuer cette activite

        finish();
    }

}
