package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Show_Conseil_Nom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__conseil__nom)

        val intent = intent
        val name = intent.getStringExtra("Name")
        val Number = intent.getIntExtra("Number", 0)

        //textview
        val resultName = findViewById<TextView>(R.id.Show_Name)
        //setText
        resultName.text = "Name: " + name

        val resultNumber = findViewById<TextView>(R.id.Show_Number)
        //setText
        resultNumber.text ="Number: " + Number
    }

    fun main(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, MainActivity::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }





}