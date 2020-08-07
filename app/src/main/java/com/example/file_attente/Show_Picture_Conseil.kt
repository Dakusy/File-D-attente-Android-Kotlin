package com.example.file_attente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.file_attente.languages.MyPreference
import kotlinx.android.synthetic.main.activity_show__picture__conseil.*

class Show_Picture_Conseil : AppCompatActivity() {

    lateinit var myPreference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__picture__conseil)
        image_view.setImageURI(image_uri)
        Show_Number.text ="Number: " + Number

        finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //Retour au départ
            startActivity(intent)
        }

    }
}






