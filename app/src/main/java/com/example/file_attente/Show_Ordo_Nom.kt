package com.example.file_attente

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.file_attente.languages.MyPreference
import com.example.file_attente.languages.Settings
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import kotlinx.android.synthetic.main.activity_show__ordo__nom.*
import java.lang.Exception

class Show_Ordo_Nom : AppCompatActivity(), PrintingCallback {

    lateinit var myPreference: MyPreference
    internal var printing: Printing? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__ordo__nom)

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

        Btn_Retour.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                startActivityForResult(
                    Intent(this, ScanningActivity::class.java),
                    ScanningActivity.SCANNING_FOR_PRINTER
                )
            } else {
                printText()
                Thread.sleep(5_000)
                val intent = Intent(this, MainActivity::class.java)
                //Retour au départ
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
                .setText(Number.toString())
                .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
                .setNewLinesAfter(1)
                .build()
        )

        printing!!.print(printables)

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

