package com.example.pillservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ChangeAppName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_app_name)
        initListeners()
    }

    private fun initListeners() {
        val changeNameBtn = findViewById<Button>(R.id.changeNameButton)
        changeNameBtn.setOnClickListener(changeNameBtnListener)
    }

    private val changeNameBtnListener: View.OnClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.changeNameButton -> {
                returnData()
            }
        }
    }

    private fun returnData() {
        val intent = Intent()
        val editName = findViewById<EditText>(R.id.editAppName)

        if(editName.text.toString().isEmpty()) {
            setResult(RESULT_CANCELED, intent)
        } else {
            intent.putExtra("nameApp", editName.text.toString());
            setResult(RESULT_OK, intent)
        }
        Toast.makeText(applicationContext, R.string.successChange, Toast.LENGTH_LONG).show()
        finish()
    }
}