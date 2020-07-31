package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Ordonnance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordonnance)
    }

    public fun Back(v: View?) {
        //pour retourner a l’activite principale il suffit seulement d’appler la methode finish() qui vas tuer cette activite

        finish();
    }

    fun nom_ordonnance(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Nom_Ordonnance::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }

   /* fun picture_ordonnance(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Picture_Ordonnance::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }*/
}