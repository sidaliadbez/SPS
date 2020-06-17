package com.example.sps

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_urgence.*



class MapActivity : AppCompatActivity() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object{




    }
private  lateinit var map : GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttoncas.setOnClickListener {
            onMarkerClick(map.addMarker(MarkerOptions().position(LatLng(MainActivity.db.readWilaya().get(2).lag, MainActivity.db.readWilaya().get(2).lng)).title(
                MainActivity.db.readWilaya().get(2).nom)))
            if(layoutcaswilaya.visibility==View.VISIBLE)

               { layoutcaswilaya.visibility= View.GONE
                   buttoncas.setImageResource(R.drawable.ic_down)
               }else {
                    layoutcaswilaya.visibility= View.VISIBLE
                buttoncas.setImageResource(R.drawable.ic_up)
                    }


        }

        var wilayas = java.util.ArrayList<Wilaya>()
        wilayas.add(MainActivity.db.readWilaya().get(0))
        wilayas.add(MainActivity.db.readWilaya().get(2))
        wilayas.add(MainActivity.db.readWilaya().get(1))
        wilayas.add(MainActivity.db.readWilaya().get(3))
        val sorted : List<Wilaya> =  wilayas.sortedBy { it.nom }
        val sortedWilaya = ArrayList<Wilaya>()
        sorted.forEach { sortedWilaya.add(it)  }
        filtreville.setOnClickListener {
            val popupmenu = PopupMenu(this, it)
            popupmenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.namewilaya -> {
                            filtreville.text="Par ville"
                        setupRecyclerView(sortedWilaya)
                        wilayas.forEach { println(it.nom) }
                        true
                    }
                    R.id.nombrecas -> {
                        filtreville.text="Par Nombre de cas"
                        wilayas.sortByDescending  { it.nbcas }
                        setupRecyclerView(wilayas)
                        true
                    }
                    else -> false
                }
            }
            popupmenu.inflate(R.menu.menu_filter)
            popupmenu.show()
        }

        setupRecyclerView(sortedWilaya)
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            map = p0
        }

        val Alger: LatLng= LatLng(MainActivity.db.readWilaya().get(2).lag, MainActivity.db.readWilaya().get(2).lng)
        map.addMarker(MarkerOptions().position(Alger).title(MainActivity.db.readWilaya().get(2).nom))
        val Bejaia: LatLng= LatLng(MainActivity.db.readWilaya().get(0).lag, MainActivity.db.readWilaya().get(0).lng)
        map.addMarker(MarkerOptions().position(Bejaia).title(MainActivity.db.readWilaya().get(0).nom))
        val Oran: LatLng= LatLng(MainActivity.db.readWilaya().get(3).lag, MainActivity.db.readWilaya().get(3).lng)
        map.addMarker(MarkerOptions().position(Oran).title(MainActivity.db.readWilaya().get(3).nom))
        val Annaba: LatLng= LatLng(MainActivity.db.readWilaya().get(1).lag, MainActivity.db.readWilaya().get(1).lng)
        map.addMarker(MarkerOptions().position(Annaba).title(MainActivity.db.readWilaya().get(1).nom))
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

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }
}
