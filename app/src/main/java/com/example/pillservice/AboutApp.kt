package com.example.pillservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class AboutApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        initListeners()
    }

    private fun initListeners() {
        val changeAppNameBtn = findViewById<Button>(R.id.changeAppName)
        changeAppNameBtn.setOnClickListener(changeAppNameBtnListener)
    }

    val changeAppNameBtnListener: View.OnClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.changeAppName -> {
                val i = Intent(this, ChangeAppName::class.java);
                startActivityForResult(i, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);

        var nameApp: String = ""

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                if(data!!.getStringExtra("nameApp")!!.isNotEmpty()) {
                    nameApp = data.getStringExtra("nameApp")!!;
                } else {
                    nameApp = "No name"
                }
            }
        }
        val appName = findViewById<TextView>(R.id.appName);
        appName.text = nameApp;
    }
}