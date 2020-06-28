package com.example.sps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardalerte.view.*

class ListeUrgenceAdapter (val context: Context,private val Urgences :  ArrayList<Urgence>): RecyclerView.Adapter<ListeUrgenceAdapter.MyViewHolder>(){
   inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun setData(note: Urgence?, pos: Int) {
           itemView.nomurgence.text = note?.nom
           itemView.contenurgence.text = note?.contenu
           itemView.appeler.setText(note?.phone)
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardalerte, parent, false)
        return MyViewHolder(view)    }

    override fun getItemCount(): Int {
        return Urgences.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val urgence = Urgences[position]
        holder.setData(urgence,position)
    }

}