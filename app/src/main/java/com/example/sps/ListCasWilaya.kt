package com.example.sps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardalerte.view.*
import kotlinx.android.synthetic.main.layout_caswilaya.view.*

class ListCasWilaya (val context: Context,private val wilayas :  ArrayList<Wilaya>): RecyclerView.Adapter<ListCasWilaya.MyViewHolder>(){
    inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(note: Wilaya?, pos: Int) {
            itemView.nomwilaya.text = note?.nom
            itemView.nbcas.text = note?.nbcas.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_caswilaya, parent, false)
        return MyViewHolder(view)    }

    override fun getItemCount(): Int {
        return wilayas.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val wilaya = wilayas[position]
        holder.setData(wilaya,position)
    }



}