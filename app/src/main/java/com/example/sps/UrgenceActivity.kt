package com.example.sps

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sps.MainActivity.Companion.db
import kotlinx.android.synthetic.main.activity_urgence.*


class UrgenceActivity : AppCompatActivity() {
companion object{
   // var urgences= ArrayList<Urgence>()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgence)
        supportActionBar?.title="Urgences et Alertes"
//        val Urgence =Urgence("Urgence 1 ", "Ceci est une Urgence")
//        val Urgence2 =Urgence("Urgence 2 ", "Ceci est une Urgence")


//        urgences.add(Urgence)
//        urgences.add(Urgence2)
        setupRecyclerView()


        fab.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
           val dialogueUrgence= DialogueUrgence()
        dialogueUrgence.show(supportFragmentManager,"example dialogue")
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        val adapter = ListeUrgenceAdapter(
            this,
             db.readData() as ArrayList<Urgence>
        )
        recyclerview.adapter = adapter
    }
}
