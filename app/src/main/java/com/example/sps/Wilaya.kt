package com.example.sps

import com.google.android.gms.maps.model.LatLng

class Wilaya {
    lateinit var  nom :String
    var nbcas :Int=0
    var  lag: Double = 0.0
     var lng: Double=0.0
    var id: Int =0
constructor(nom :String,nbcas :Int, lag: Double,lng: Double){
    this.nom=nom
    this.nbcas=nbcas
    this.lag=lag
    this.lng=lng

}
    constructor(){}

}