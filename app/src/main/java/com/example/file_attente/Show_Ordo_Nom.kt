package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_show__ordo__nom.*

class Show_Ordo_Nom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__ordo__nom)

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

        Btn_Retour.setOnClickListener {
            val intent = Intent(this@Show_Ordo_Nom, MainActivity::class.java)
            startActivity(intent)
        }
    }
}