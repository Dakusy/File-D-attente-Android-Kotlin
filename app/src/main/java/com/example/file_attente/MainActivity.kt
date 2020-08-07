package com.example.file_attente

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.file_attente.languages.MyContextWrapper
import com.example.file_attente.languages.MyPreference
import com.example.file_attente.languages.Settings
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    lateinit var myPreference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Permet de garder en compte la langue préféré selectionner dans les options.
    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase,lang))
    }

    //Redirection vers l'activité Ordonnance
    fun ordonnance(v: View?) {
        val intent = Intent(this, Ordonnance::class.java)
        startActivity(intent)
    }

    //Redirection vers les options pour la langue
    fun lang(v: View?) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    //Redirection vers l'activité Conseil
    fun conseil(v: View?) {
        val intent = Intent(this, Conseil::class.java)
        startActivity(intent)
    }


}
