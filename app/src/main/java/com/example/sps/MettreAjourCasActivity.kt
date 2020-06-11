package com.example.sps

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mettreajourcas.*
import java.text.SimpleDateFormat
import java.util.*


class MettreAjourCasActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {
    var formate = SimpleDateFormat("dd MMM, YYYY",Locale.FRENCH)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.FRANCE)
    lateinit var radioGroup:RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mettreajourcas)
        val currentTime = Calendar.getInstance().time
        val date = formate.format(currentTime.time)
        textdate.text=date
        val time = timeFormat.format(currentTime.time)
        texttime.text=time
        val adapter = ArrayAdapter.createFromResource(this,R.array.array_ressource,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerwilaya.adapter=adapter
        spinnerwilaya.onItemSelectedListener=this

        radioGroup =findViewById<RadioGroup>(R.id.grouperadio)
        buttonannuler.setOnClickListener {
            finish()
        }
        selecttime.setOnClickListener {
            val now = Calendar.getInstance()
            try {
                if(selecttime.text != "Show Dialog") {
                    val date = timeFormat.parse(selecttime.text.toString())
                    now.time = date
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                texttime.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()
        }

        selectdate.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)
                textdate.text=date
            },
                    now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
    }

    override fun finish() {
        super.finish()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       val text = parent?.getItemAtPosition(position).toString()
        if (parent != null) {
            Toast.makeText(parent.context,text,Toast.LENGTH_SHORT).show()
        }
    }
    fun checkButton(v: View?) {
        val radioId: Int? = radioGroup.checkedRadioButtonId
        //var radioButton = radioId?.let { findViewById<RadioButton>(it) }
       var radioButton = radioId?.let { findViewById<View>(it) } as RadioButton?
        Toast.makeText(
            this, "Selected Radio Button: " + radioButton?.text,
            Toast.LENGTH_SHORT
        ).show()
    }
}