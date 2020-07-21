package com.example.file_attente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_picture__conseil.*
import kotlinx.android.synthetic.main.activity_picture__conseil.image_view
import kotlinx.android.synthetic.main.activity_show__picture__conseil.*

class Show_Picture_Conseil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__picture__conseil)
        image_view.setImageURI(image_uri)
        Show_Number.text ="Number: " + Number
    }
}