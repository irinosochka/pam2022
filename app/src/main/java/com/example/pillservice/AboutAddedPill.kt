package com.example.pillservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AboutAddedPill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_added_pill)

        var resultLabel = findViewById<TextView>(R.id.result)
        val arguments = intent.extras

        if(arguments!=null) {
            var resultPill = arguments.getString("result")
            resultLabel.text = "${getString(R.string.youAdd)}: $resultPill"
        }
    }
}