package com.example.pillservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        val pillAddBtn = findViewById<Button>(R.id.pillAddButton)
        val aboutAppBtn = findViewById<Button>(R.id.aboutAppButton)
        val aboutAuthorBtn = findViewById<Button>(R.id.aboutAuthorButton)

        pillAddBtn.setOnClickListener(pillAddBtnListener)
        aboutAppBtn.setOnClickListener(aboutAppBtnListener)
        aboutAuthorBtn.setOnClickListener(aboutAuthorBtnListener)
    }

    private val pillAddBtnListener = View.OnClickListener { callPillAddActivity() }
    private val aboutAppBtnListener = View.OnClickListener { callAboutAppActivity() }
    private val aboutAuthorBtnListener = View.OnClickListener { callAboutAuthorActivity() }

    private fun callPillAddActivity() {
        val runAuthorIntent = Intent(this, AddPill::class.java)
        startActivity(runAuthorIntent)
    }
    private fun callAboutAppActivity() {
        val runAuthorIntent = Intent(this, AboutApp::class.java)
        startActivity(runAuthorIntent)
    }
    private fun callAboutAuthorActivity() {
        val runAuthorIntent = Intent(this, AboutAuthor::class.java)
        startActivity(runAuthorIntent)
    }

}