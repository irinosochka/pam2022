package com.example.pillservice

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class AddPill : AppCompatActivity() {
    private var dpDay : Int = 0
    private var dpMonth : Int = 0
    private var dpYear : Int = 0
    private  var dateString : String = ""

    private lateinit var addPillBtn : Button
    private lateinit var initDateBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pill)
        initListeners()
    }

    private fun initListeners() {
        addPillBtn = findViewById(R.id.addPillButton)
        initDateBtn = findViewById(R.id.initDate)
        initDateBtn.setOnClickListener(initDateBtnListener)
        addPillBtn.setOnClickListener(addPillBtnListener)
    }

    private val addPillBtnListener = View.OnClickListener { returnPill() }
    private val initDateBtnListener = View.OnClickListener { returnDataPicker() }

    private fun returnPill() {
        val editPill = findViewById<EditText>(R.id.editPill)
        val editQuantity = findViewById<EditText>(R.id.editQuantity)

        val namePill = editPill.text.toString()
        val quantity = editQuantity.text.toString()

        if (editQuantity.text.toString().toInt() == 0) {
            basicAlert(getString(R.string.error), getString(R.string.alertQuantity))
        } else if (editPill.length() == 0 || editQuantity.length() == 0) {
            basicAlert(getString(R.string.error), getString(R.string.alertEmptyValue))
        } else if (dateString == "") {
            basicAlert(getString(R.string.error), getString(R.string.alertEmptyData))
        } else {
            runAboutAddedPill("$namePill , \n${getString(R.string.quantityOfPills)} : $quantity \n" +
             "${getString(R.string.date)} : $dateString.")
            Toast.makeText(applicationContext, R.string.successAdd, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun basicAlert(titleAlert: String, messageAlert: String) {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(titleAlert)
            setMessage(messageAlert)
            setPositiveButton("ОК", null)
            show()
        }
    }

    fun runAboutAddedPill(value: String?) {
        val runSecondIntent = Intent(this, AboutAddedPill::class.java)
        runSecondIntent.putExtra("result", value)
        startActivity(runSecondIntent)
    }

    private fun returnDataPicker() {
        val cal = Calendar.getInstance()
        dpYear = cal[Calendar.YEAR]
        dpMonth = cal[Calendar.MONTH]
        dpDay = cal[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                if((monthOfYear + 1) > 9){
                    dateString = dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                } else {
                    dateString = dayOfMonth.toString() + ".0" + (monthOfYear + 1) + "." + year
                }
            }, dpYear, dpMonth, dpDay
        )
        datePickerDialog.show()
    }
//
//    private fun AddPill(){
//        val editPill = findViewById<EditText>(R.id.editPill)
//        val editQuantity = findViewById<EditText>(R.id.editQuantity)
//
//        val namePill = editPill.text.toString()
//        val quantityString = editQuantity.text.toString()
//        val quantity = Integer.parseInt(quantityString)
//        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
//        val pill = Pill(namePill, quantity, dateString )
//        dataBaseHandler.addPill(pill)
//        Toast.makeText(applicationContext, R.string.successAdd, Toast.LENGTH_LONG).show()
//    }
}