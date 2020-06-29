package com.example.sps

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sps.MainActivity.Companion.db
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_mettreajourcas.*
import java.text.SimpleDateFormat
import java.util.*


class MettreAjourCasActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var formate = SimpleDateFormat("dd MMM, YYYY",Locale.FRENCH)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.FRANCE)
    lateinit var radioGroup:RadioGroup
    lateinit var wilaya: String



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

        val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        val menu = bottomNavigationView.menu
        val menuItem = menu.getItem(0)
        menuItem.isChecked = true
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    true
                }
                R.id.page_4 -> {
                    val intent = Intent(this,GuerisonMortActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    true
                }
                else->false
            }
            true
        }
        buttonenregistrer.setOnClickListener {
            val radioId: Int = radioGroup.checkedRadioButtonId
            var radioButton = radioId?.let { findViewById<View>(it) } as RadioButton?
            val cas = cas(1,wilaya,
                radioButton?.text.toString(),age.text.toString(),texttime.text.toString(),textdate.text.toString())
           // GuerisonMortActivity.cass.add(cas)
            db.addCas(cas)
            Toast.makeText(this,"Enregistré Avec Succés ",Toast.LENGTH_SHORT).show()

            when(wilaya){
                "Alger"->{
                    db.updateWilaya(db.readWilaya().get(2).id.toString(),"alger",db.readWilaya().get(2).nbcas+1,db.readWilaya().get(2).lag,db.readWilaya().get(2).lng)
                }
                "Oran"->{
                    db.updateWilaya(db.readWilaya().get(3).id.toString(),"oran",db.readWilaya().get(3).nbcas+1,db.readWilaya().get(3).lag,db.readWilaya().get(3).lng)
                }
                "Annaba"->{
                    db.updateWilaya(db.readWilaya().get(1).id.toString(),"annaba",db.readWilaya().get(1).nbcas+1,db.readWilaya().get(1).lag,db.readWilaya().get(1).lng)

                }
                "Bejaia"->{
                    db.updateWilaya(db.readWilaya().get(0).id.toString(),"bejaia",db.readWilaya().get(0).nbcas+1,db.readWilaya().get(0).lag,db.readWilaya().get(0).lng)
                }
            }

        }
    }








    override fun finish() {
        super.finish()
        val intent = Intent(this,RecueilActivity::class.java)
        startActivity(intent)

    }
   override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       val text = parent?.getItemAtPosition(position).toString()
        if (parent != null) {
            //Toast.makeText(parent.context,text,Toast.LENGTH_SHORT).show()
            wilaya=text
        }
    }

    fun checkButton(v: View?) {
        val radioId: Int = radioGroup.checkedRadioButtonId
        //var radioButton = radioId?.let { findViewById<RadioButton>(it) }
       var radioButton = radioId?.let { findViewById<View>(it) } as RadioButton?
//        Toast.makeText(
//            this, "Selected Radio Button: " + radioButton?.text,
//            Toast.LENGTH_SHORT
//        ).show()
    }
}