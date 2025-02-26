package com.example.assignment6aaravrathid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val name=findViewById<EditText>(R.id.editTextText3)
        val submit=findViewById<Button>(R.id.button2)
        val updateName=findViewById<TextView>(R.id.textView)


       submit.setOnClickListener{
           val userInput=name.text.toString()
           updateName.text="Hello $userInput";
       }


    }
}