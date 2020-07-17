package com.example.file_attente

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat

class Nom_Conseil : AppCompatActivity() {


     var Number: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nom__conseil)

        val Name = findViewById<EditText>(R.id.editTextTextPersonName)
        val submitBtn = findViewById<Button>(R.id.submit)


        submitBtn.setOnClickListener {
            //get text from edittexts
            val name = Name.text.toString()
            Number += 1
            //intent to start activity
            val intent = Intent(this@Nom_Conseil, Show_Conseil_Nom::class.java)
            intent.putExtra("Name", name)
            intent.putExtra("Number", Number)
            startActivity(intent)
        }
    }
    public fun Back(v: View?) {
        //pour retourner a l’activite principale il suffit seulement d’appler la methode finish() qui vas tuer cette activite

        finish();
    }




 /*   var Notif = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("chocolat")
        .setContentText("test")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }*/
}