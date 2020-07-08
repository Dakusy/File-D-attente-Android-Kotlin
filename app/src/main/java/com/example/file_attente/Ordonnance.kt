package com.example.file_attente

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
}