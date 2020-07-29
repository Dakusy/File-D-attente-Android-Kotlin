package com.example.file_attente

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import java.util.*



class MainActivity : AppCompatActivity(), PrintingCallback {

    internal var printing: Printing?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initView()
    }
    fun ordonnance(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Ordonnance::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
        startActivity(intent)
    }


    private fun initView(){
        if(printing!= null){
            printing!!.printingCallback = this

            button.setOnClickListener {
                if(Printooth.hasPairedPrinter()){
                    Printooth.removeCurrentPrinter()
                }
                else{
                    startActivityForResult(Intent(this@MainActivity,ScanningActivity::class.java),
                    ScanningActivity.SCANNING_FOR_PRINTER)
                    changePairAndUnpair()
                }
            }
        }
    }

    private fun changePairAndUnpair(){
        if(Printooth.hasPairedPrinter()){
            button.text = "Unpair  ${Printooth.getPairedPrinter()?.name}"
        } else {
            button.text = "Pair with printer"
        }
    }
    fun conseil(v: View?) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        val intent = Intent(this, Conseil::class.java)
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Conseil
        startActivity(intent)
    }

    //Permet de changer la langue avec des radio buttons.
    public fun radio_button_click(v: View?) {
        //Si check changement de langue
        if(en.isChecked){
            setLocate("en")
        }
        else if(fr.isChecked){
            setLocate("fr")
        }

        else if(es.isChecked){
            setLocate("es")
        }
        recreate()

    }
    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }


    override fun recreate() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.recreate()
        } else {
            startActivity(intent)
            finish()
        }
    }

    override fun connectingWithPrinter() {
        TODO("Not yet implemented")
    }

    override fun connectionFailed(error: String) {
        TODO("Not yet implemented")
    }

    override fun onError(error: String) {
        TODO("Not yet implemented")
    }

    override fun onMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun printingOrderSentSuccessfully() {
        TODO("Not yet implemented")
    }


}