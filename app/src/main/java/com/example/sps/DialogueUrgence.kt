package com.example.sps

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.activity_urgence.*
import kotlinx.android.synthetic.main.activity_urgence.view.*
import kotlinx.android.synthetic.main.layout_dialoguealerte.*
import kotlinx.android.synthetic.main.layout_dialoguealerte.view.*


public  class DialogueUrgence : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val dialogView = LayoutInflater.from(activity).inflate(com.example.sps.R.layout.layout_dialoguealerte,null)
        val view = LayoutInflater.from(activity).inflate(com.example.sps.R.layout.activity_urgence,null)
        builder.setView(dialogView)
            .setTitle("Ajouter Alerte")
            .setNegativeButton("Annuler",DialogInterface.OnClickListener { dialog, which ->  })
            .setPositiveButton("Ok",DialogInterface.OnClickListener { dialog, which ->
                      println("HEEEEEEEEEEELLOOOO")
                val name= dialogView.edit_username.text.toString()
                val contenu= dialogView.edit_password.text.toString()
                val phone= dialogView.edit_phoen.text.toString()
                if (name!="" && contenu != "" && phone!=""){
                    val urgence = Urgence(name,contenu,phone)
                    MainActivity.db.addData(urgence)
                    val intent = Intent(context,UrgenceActivity::class.java)
                    startActivity(intent)
                    //UrgenceActivity.urgences.add(urgence)
                }else         Toast.makeText(activity,"Veuillez remplir tous les données", Toast.LENGTH_SHORT).show()

                //view.nomedecin.text=name
            })
        return builder.create()
    }

}