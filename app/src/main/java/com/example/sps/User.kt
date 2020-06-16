package com.example.sps

import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.io.OutputStream
import java.io.OutputStreamWriter
import android.os.Bundle

private val CSV_HEADER = "Username,Password,Nom,Prenom,Age"
class User {

    var id: String? = null
    var nom: String? = null
    var prenom: String? = null
    var numero: Int = 0
    var pwd :String? = null

    constructor() {}
    constructor(id: String?, name: String?, prenom: String?, num: Int,pwd: String?) {
        this.id = id
        this.nom = name
        this.prenom = prenom
        this.numero = num
        this.pwd = pwd
    }

    override fun toString(): String {
        return   id + "," +pwd+","+ nom + "," + prenom + "," + numero
    }





}