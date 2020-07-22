package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_picture__conseil.*
import kotlinx.android.synthetic.main.activity_picture__conseil.image_view
import kotlinx.android.synthetic.main.activity_show__conseil__nom.*
import kotlinx.android.synthetic.main.activity_show__picture__conseil.*
import kotlinx.android.synthetic.main.activity_show__picture__conseil.Btn_Retour
import kotlinx.android.synthetic.main.activity_show__picture__conseil.Show_Number

class Show_Picture_Conseil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__picture__conseil)
        image_view.setImageURI(image_uri)
        Show_Number.text ="Number: " + Number

        Btn_Retour.setOnClickListener {
            val intent = Intent(this@Show_Picture_Conseil, MainActivity::class.java)
            startActivity(intent)
        }
    }



}