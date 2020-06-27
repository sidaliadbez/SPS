package com.example.sps

class cas {
    var type: Int=0
    lateinit var wilaya: String
    lateinit var caracteristique1: String
    lateinit var caracteristique2: String
    lateinit var time: String
    lateinit var date: String
    var id :Int = 0
constructor(type: Int,wilaya: String,caracteristique1: String,caracteristique2: String,time: String,date: String){
    this.type=type
    this.wilaya= wilaya
    this.caracteristique1=caracteristique1
    this.caracteristique2=caracteristique2
    this.time=time
    this.date=date
}
    constructor(){}
}