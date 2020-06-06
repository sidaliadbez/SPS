package com.example.sps

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_urgence.*


class UrgenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgence)
        val Urgence =Urgence("Urgence 1 ", "Ceci est une Urgence")
        val Urgence2 =Urgence("Urgence 2 ", "Ceci est une Urgence")
         var urgences= ArrayList<Urgence>()

    urgences.add(Urgence)
        urgences.add(Urgence2)
        setupRecyclerView(urgences)


        fab.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
           val dialogueUrgence= DialogueUrgence()
        dialogueUrgence.show(supportFragmentManager,"example dialogue")
    }

    private fun setupRecyclerView(urgences: ArrayList<Urgence>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        val adapter = ListeUrgenceAdapter(
            this,
             urgences
        )
        recyclerview.adapter = adapter
    }
}
