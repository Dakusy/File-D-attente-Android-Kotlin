package com.example.file_attente

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mongodb.DB
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val mongoClient = MongoClient(MongoClientURI("mongodb://localhost:27017"))
    }


    fun ordonnance(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Ordonnance::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }

    fun conseil(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Conseil::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Conseil
        startActivity(intent)
    }
    //Permet de changer la langue avec des radio buttons.
    public fun radio_button_click(v: View?) {
        //Si check changement de langue
        if(en.isChecked){
            setLocate("en")
        }
        else if(fr.isChecked){
            setLocate("fr")
        }

        else if(es.isChecked){
            setLocate("es")
        }
        recreate()

    }
    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }


    override fun recreate() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.recreate()
        } else {
            startActivity(intent)
            finish()
        }
    }



}