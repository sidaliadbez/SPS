package com.example.sps

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mettreajourcas.*


class MettreAjourCasActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mettreajourcas)
        val adapter = ArrayAdapter.createFromResource(this,R.array.array_ressource,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerwilaya.adapter=adapter
        spinnerwilaya.onItemSelectedListener=this
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
}