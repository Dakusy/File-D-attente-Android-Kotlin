package com.example.file_attente

import android.app.Application
import com.mazenrashed.printooth.Printooth

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        //Permet d'initialiser Printooth, notre librairie pour l'imprimante Bluetooth.
        Printooth.init(this);

    }

}