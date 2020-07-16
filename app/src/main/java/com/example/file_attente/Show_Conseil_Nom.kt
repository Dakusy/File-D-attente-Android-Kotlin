package com.example.file_attente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Show_Conseil_Nom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__conseil__nom)

        val intent = intent
        val name = intent.getStringExtra("Name")
        val Number = intent.getIntArrayExtra("Number")

        //textview
        val resultTv = findViewById<TextView>(R.id.Show_Name)
        //setText
        resultTv.text = "Name: " + name + "Number : " + Number

    }



}