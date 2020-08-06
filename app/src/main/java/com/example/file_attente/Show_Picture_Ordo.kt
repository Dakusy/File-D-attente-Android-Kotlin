package com.example.file_attente

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.file_attente.languages.MyPreference
import kotlinx.android.synthetic.main.activity_show__picture__ordo.*


class Show_Picture_Ordo : AppCompatActivity() {

    lateinit var myPreference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__picture__ordo)
        image_view.setImageURI(image_uri)
        Show_Number.text ="Number: " + Number

        finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //Retour au départ
            startActivity(intent)
        }
    }
}
