package com.example.sps

class Urgence {
    var nom : String="Urgence"
    var contenu : String ="-"
    var phone : String ="05"
    var id : Int = 0

    constructor(nom: String, contenu: String,phone :String="0707") {
        this.nom = nom
        this.contenu = contenu
        this.phone = phone
    }
    constructor(){

    }
}