package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_nom__conseil.*
import kotlinx.android.synthetic.main.activity_show__conseil__nom.*

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

        Btn_Retour.setOnClickListener {
            val intent = Intent(this@Show_Conseil_Nom, MainActivity::class.java)
            startActivity(intent)
        }
    }


}