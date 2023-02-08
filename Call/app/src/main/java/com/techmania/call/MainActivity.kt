package com.techmania.call

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var phone : EditText
    lateinit var call : Button

    var userNumber : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call = findViewById(R.id.buttonCall)
        phone = findViewById(R.id.editTextPhone)

        call.setOnClickListener {

            userNumber = phone.text.toString()
            startCall(userNumber)

        }

    }

    fun startCall(userNumber : String){

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),100)

        }else{
            //val intent = Intent(Intent.ACTION_DIAL)
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$userNumber")
            startActivity(intent)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$userNumber")
            startActivity(intent)

        }

    }

}