package com.example.aitonify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Wellcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)
        val btn = findViewById<Button>(R.id.button1)

        btn.setOnClickListener {
            startActivity(Intent(this@Wellcome, Description::class.java))
        }

    }
}