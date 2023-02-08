package com.techmania.sendsms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var message : EditText
    lateinit var number : EditText
    lateinit var send : Button

    var userMessage : String = ""
    var userNumber : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        message = findViewById(R.id.editTextMessage)
        number = findViewById(R.id.editTextNumber)
        send = findViewById(R.id.buttonSend)

        send.setOnClickListener {

            userMessage = message.text.toString()
            userNumber = number.text.toString()

            sendSMS(userMessage, userNumber)

        }

    }

    fun sendSMS(userMessage : String,userNumber : String){

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1)
        }
        else
        {
            val smsManager : SmsManager
            if (Build.VERSION.SDK_INT >= 23){
                smsManager = this.getSystemService(SmsManager::class.java)
            }else{
                smsManager = SmsManager.getDefault()
            }

            smsManager.sendTextMessage(userNumber,null,userMessage,null,null)

            Toast.makeText(applicationContext,"Message Sent",Toast.LENGTH_LONG).show()

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            val smsManager : SmsManager
            if (Build.VERSION.SDK_INT >= 23){
                smsManager = this.getSystemService(SmsManager::class.java)
            }else{
                smsManager = SmsManager.getDefault()
            }

            smsManager.sendTextMessage(userNumber,null,userMessage,null,null)

            Toast.makeText(applicationContext,"Message Sent",Toast.LENGTH_LONG).show()
        }

    }

}