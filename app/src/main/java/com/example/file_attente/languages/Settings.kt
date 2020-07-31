package com.example.file_attente.languages

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.file_attente.MainActivity
import com.example.file_attente.R
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {
    lateinit var myPreference: MyPreference
    lateinit var context: Context

    val languageList = arrayOf("en","fr","es")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        context = this
        myPreference = MyPreference(this)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,languageList)

        val lang = myPreference.getLoginCount()
        val index = languageList.indexOf(lang)
        if(index >= 0){
            spinner.setSelection(index)
        }

        validate.setOnClickListener {
            myPreference.setLoginCount(languageList[spinner.selectedItemPosition])
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

