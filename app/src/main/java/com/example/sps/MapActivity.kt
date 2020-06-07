package com.example.sps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_urgence.*


class MapActivity : AppCompatActivity() , OnMapReadyCallback {
    companion object{
        var bejaia = Wilaya("bejaia",7,36.75587 ,5.08433)
        var annaba = Wilaya("annaba",10,36.9 ,7.76667)
        var alger = Wilaya("alger",9,36.754110,3.058785)
        var oran = Wilaya("oran",5,35.69111 ,-0.64167)
    }
private  lateinit var map : GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttoncas.setOnClickListener {
                layoutcaswilaya.visibility= View.GONE
        }
        var wilayas = java.util.ArrayList<Wilaya>()
        wilayas.add(bejaia)
        wilayas.add(alger)
        setupRecyclerView(wilayas)
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            map = p0
        }

        val Alger: LatLng= LatLng(alger.lag, alger.lng)
        map.addMarker(MarkerOptions().position(Alger).title(alger.nom))
        val Bejaia: LatLng= LatLng(bejaia.lag, bejaia.lng)
        map.addMarker(MarkerOptions().position(Bejaia).title(bejaia.nom))
        val Oran: LatLng= LatLng(oran.lag, oran.lng)
        map.addMarker(MarkerOptions().position(Oran).title(oran.nom))
        val Annaba: LatLng= LatLng(annaba.lag, annaba.lng)
        map.addMarker(MarkerOptions().position(Annaba).title(annaba.nom))
        val centrealgerie : LatLng= LatLng(30.965656,1.663521)
        map.moveCamera(CameraUpdateFactory.newLatLng(centrealgerie))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(centrealgerie, 5f))
    }
    private fun setupRecyclerView(wilayas: ArrayList<Wilaya>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listcaswilaya.layoutManager = layoutManager
        val adapter = ListCasWilaya(
            this,
            wilayas
        )
        listcaswilaya.adapter = adapter
    }
}
