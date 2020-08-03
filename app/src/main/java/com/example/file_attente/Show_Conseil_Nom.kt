package com.example.file_attente

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.file_attente.languages.MyPreference
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_show__conseil__nom.*


import java.lang.Exception

class Show_Conseil_Nom : AppCompatActivity(), PrintingCallback {

    lateinit var myPreference: MyPreference
    internal var printing: Printing? = null;

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
        resultNumber.text = "Number: " + Number
        initView()
    }

        private fun initView() {
            if (printing != null) {
                printing!!.printingCallback = this
            }
            btn_PairUnPair.setOnClickListener {
                if (Printooth.hasPairedPrinter()) {
                    Printooth.removeCurrentPrinter()
                } else {
                    startActivityForResult(
                        Intent(this, ScanningActivity::class.java),
                        ScanningActivity.SCANNING_FOR_PRINTER
                    )
                    changePairAndUnpair()
                }
            }

            btn_Retour.setOnClickListener {
                if (!Printooth.hasPairedPrinter()) {
                    startActivityForResult(
                        Intent(this, ScanningActivity::class.java),
                        ScanningActivity.SCANNING_FOR_PRINTER
                    )
                } else {
                    printText()
                    Thread.sleep(5_000)
                    //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
                    val intent = Intent(this, MainActivity::class.java)
                    //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici Ordonnance
                    startActivity(intent)
                }

            }
        }

        private fun changePairAndUnpair() {
            if (Printooth.hasPairedPrinter()) {
                btn_PairUnPair.text = "Unpair  ${Printooth.getPairedPrinter()!!.name}"
            } else {
                btn_PairUnPair.text = "Pair with printer"
            }
        }

        override fun connectingWithPrinter() {
            Toast.makeText(this, "Connecting to printer", Toast.LENGTH_SHORT).show()
        }

        override fun connectionFailed(error: String) {
            Toast.makeText(this, "Failed: $error", Toast.LENGTH_SHORT).show()
        }

        override fun onError(error: String) {
            Toast.makeText(this, "Error : $error", Toast.LENGTH_SHORT).show()
        }

        override fun onMessage(message: String) {
            Toast.makeText(this, "Message : $message", Toast.LENGTH_SHORT).show()
        }

        override fun printingOrderSentSuccessfully() {
            Toast.makeText(this, "Order sent to printer", Toast.LENGTH_SHORT).show()
        }

        private fun printText() {
            val printables = ArrayList<Printable>()
            printables.add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build())

            //Custom Text

            printables.add(
                TextPrintable.Builder()
                    .setText(com.example.file_attente.Number.toString())
                    .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
                    .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                    .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
                    .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                    .setNewLinesAfter(3)
                    .build()
            )

            printing!!.print(printables)

        }

        private fun printImage() {
            val printables = ArrayList<Printable>()

            //load Bitmap from internet
            Picasso.get()
                .load("https://icon-library.com/images/facebook-icon-50x50/facebook-icon-50x50-25.jpg")
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Toast.makeText(this@Show_Conseil_Nom, "failed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        printables.add(ImagePrintable.Builder(bitmap!!).build())
                        printing!!.print(printables)
                    }

                })


        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK) {
                initPrinting();
                changePairAndUnpair()
            }
        }

        private fun initPrinting() {
            if (Printooth.hasPairedPrinter()) {
                printing = Printooth.printer()
            }
            if (printing != null) {
                printing!!.printingCallback = this
            }
        }
    }
