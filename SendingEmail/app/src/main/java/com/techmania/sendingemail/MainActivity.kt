package com.techmania.sendingemail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techmania.sendingemail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.buttonSend.setOnClickListener {

            val userAddress = mainBinding.editTextAddress.text.toString()
            val userSubject = mainBinding.editTextSubject.text.toString()
            val userMessage = mainBinding.editTextMessage.text.toString()

            sendEmail(userAddress, userSubject, userMessage)

        }

    }

    fun sendEmail(userAddress : String,userSubject : String, userMessage : String){

        val emailAddresses = arrayOf(userAddress)

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        //emailIntent.type = "*/*"
        emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,userSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT,userMessage)

        if (emailIntent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(emailIntent,"Send Email"))
        }

    }

}