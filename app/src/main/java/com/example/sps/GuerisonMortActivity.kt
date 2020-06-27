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
import kotlinx.android.synthetic.main.activity_guerisonmort.*
import kotlinx.android.synthetic.main.activity_mettreajourcas.*
import kotlinx.android.synthetic.main.activity_mettreajourcas.buttonannuler
import kotlinx.android.synthetic.main.activity_mettreajourcas.buttonenregistrer
import kotlinx.android.synthetic.main.activity_mettreajourcas.selectdate
import kotlinx.android.synthetic.main.activity_mettreajourcas.selecttime
import kotlinx.android.synthetic.main.activity_mettreajourcas.spinnerwilaya
import kotlinx.android.synthetic.main.activity_mettreajourcas.textdate
import kotlinx.android.synthetic.main.activity_mettreajourcas.texttime
import java.text.SimpleDateFormat
import java.util.*

class GuerisonMortActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.FRENCH)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.FRANCE)
    lateinit var radioGroup: RadioGroup
    lateinit var wilaya: String
companion object{
    var cass= ArrayList<cas>()
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guerisonmort)



        val currentTime = Calendar.getInstance().time
        val date = formate.format(currentTime.time)
        textdate1.text=date
        val time = timeFormat.format(currentTime.time)
        texttime1.text=time
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
                texttime1.text = timeFormat.format(selectedTime.time)
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
                textdate1.text=date
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        val menu = bottomNavigationView.menu
        val menuItem = menu.getItem(1)
        menuItem.isChecked = true
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.page_1 -> {
                    val intent = Intent(this,MettreAjourCasActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    true
                }
                R.id.page_4 -> {

                }

            }
            true
        }
        buttonenregistrer1.setOnClickListener {
            val radioId: Int = radioGroup.checkedRadioButtonId
            var radioButton = radioId?.let { findViewById<View>(it) } as RadioButton?
            if (radioButton?.text.toString()=="Guerison"){
            val cas = cas(2,wilaya,radioButton?.text.toString(),nbcas.text.toString(),texttime1.text.toString(),textdate1.text.toString())
                db.addCas(cas)
            }else{
                val cas = cas(3,wilaya,radioButton?.text.toString(),nbcas.text.toString(),texttime1.text.toString(),textdate1.text.toString())
                db.addCas(cas)
            }
            Toast.makeText(this,"Enregistré Avec Succés ",Toast.LENGTH_SHORT).show()
            when(wilaya){
                "Alger"->{
                    MainActivity.db.updateWilaya(
                        MainActivity.db.readWilaya().get(2).id.toString(),"alger",
                        MainActivity.db.readWilaya().get(2).nbcas-nbcas.text.toString().toInt(),
                        MainActivity.db.readWilaya().get(2).lag,
                        MainActivity.db.readWilaya().get(2).lng)
                }
                "Oran"->{
                    MainActivity.db.updateWilaya(
                        MainActivity.db.readWilaya().get(3).id.toString(),"oran",
                        MainActivity.db.readWilaya().get(3).nbcas-nbcas.text.toString().toInt(),
                        MainActivity.db.readWilaya().get(3).lag,
                        MainActivity.db.readWilaya().get(3).lng)
                }
                "Annaba"->{
                    MainActivity.db.updateWilaya(
                        MainActivity.db.readWilaya().get(1).id.toString(),"annaba",
                        MainActivity.db.readWilaya().get(1).nbcas-nbcas.text.toString().toInt(),
                        MainActivity.db.readWilaya().get(1).lag,
                        MainActivity.db.readWilaya().get(1).lng)

                }
                "Bejaia"->{
                    MainActivity.db.updateWilaya(
                        MainActivity.db.readWilaya().get(0).id.toString(),"bejaia",
                        MainActivity.db.readWilaya().get(0).nbcas-nbcas.text.toString().toInt(),
                        MainActivity.db.readWilaya().get(0).lag,
                        MainActivity.db.readWilaya().get(0).lng)
                }
            }
        }
    }
    override fun finish() {
        super.finish()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text = parent?.getItemAtPosition(position).toString()
        if (parent != null) {
//            Toast.makeText(parent.context,text,Toast.LENGTH_SHORT).show()
            wilaya=text
        }    }
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