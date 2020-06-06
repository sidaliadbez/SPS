package com.example.sps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity() , OnMapReadyCallback {
private  lateinit var map : GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            map = p0
        }
        val Alger: LatLng= LatLng(36.754110, 3.058785)
        val centrealgerie : LatLng= LatLng(28.388792, 2.674263)
        map.addMarker(MarkerOptions().position(Alger).title("Alger"))
        map.moveCamera(CameraUpdateFactory.newLatLng(centrealgerie))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(centrealgerie, 5f))
    }
}
