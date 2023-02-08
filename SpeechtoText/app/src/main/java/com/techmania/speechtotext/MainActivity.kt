package com.techmania.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var resultText : TextView
    lateinit var imageButton: ImageButton

    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.textView)
        imageButton = findViewById(R.id.imageButton)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->

                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == RESULT_OK && data != null){

                    val speakResult : ArrayList<String> = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                    resultText.text = speakResult[0]

                }

            })

        imageButton.setOnClickListener {

            convertSpeech()

        }
    }

    fun convertSpeech(){

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        //startActivityForResult(intent,1)
        activityResultLauncher.launch(intent)

    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null){

            val speakResult : ArrayList<String> = data
                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

            resultText.text = speakResult[0]

        }

    }

     */

}