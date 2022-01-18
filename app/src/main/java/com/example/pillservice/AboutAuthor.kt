package com.example.pillservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AboutAuthor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_author)
        initListeners()
    }

    private fun initListeners() {
      val findPillBtn = findViewById<Button>(R.id.findBtn)
        findPillBtn.setOnClickListener(findPillBtnListener)
        val deletePillBtn = findViewById<Button>(R.id.deleteBtn)
        deletePillBtn.setOnClickListener(deletePillBtnListener)
    }

    private val findPillBtnListener = View.OnClickListener {
        val showPill = findViewById<EditText>(R.id.find)
        returnShowPill(showPill.text.toString()) }

    private val deletePillBtnListener = View.OnClickListener {
        val deletePill = findViewById<EditText>(R.id.delete)
        deletePill(deletePill.text.toString()) }

    private fun returnShowPill(nazwa: String?) {
        val quantity = findViewById<TextView>(R.id.findString)

        val dataBaseHandler = DataBaseHelper(this, null, null, 1)

        val pill = dataBaseHandler.findPill(nazwa.toString())

        if(pill != null){
            var q = pill.quantity.toString()
            quantity.text = "Quantity: $q"
        }
        else{
            quantity.text = "no data"
        }
    }

    fun deletePill(nazwa: String?) {
        val dataBaseHelper = DataBaseHelper(this, null, null, 1)

        val result = dataBaseHelper.deletePill(nazwa.toString())
        if (result) {
            Toast.makeText(this, "Pill deleted!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Unknown pill!", Toast.LENGTH_SHORT).show()
        }
    }
}