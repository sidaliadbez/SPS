package com.example.sps

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.blue
import androidx.drawerlayout.widget.DrawerLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.navigation.NavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import kotlinx.android.synthetic.main.activity_recueil_admin.*
import java.util.ArrayList

class RecueilActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recueil_admin)






        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        var retour_rec = findViewById(R.id.retourr) as Button
        retour_rec.setOnClickListener {
            drawerLayout.closeDrawer(navView)

        }






        val graph = findViewById(R.id.graph) as GraphView
        //val graph2 = findViewById(R.id.graph) as GraphView

        val points = arrayOf( DP(0,1),
            DP(1,4),
            DP(2,7),
            DP(3,6) ,
            DP(4,9),
            DP(5,7),
            DP(6,6) ,
            DP(7,9)

        )
        val points2 = arrayOf( DP(0,1),
            DP(1,42),
            DP(2,73),
            DP(3,26) ,
            DP(4,92),
            DP(5,72),
            DP(6,26) ,
            DP(7,92),
            DP(8,42),
            DP(9,73),
            DP(10,26) ,
            DP(11,92),
            DP(12,72)

        )




        val series1 = LineGraphSeries<DataPoint>(points)
        val series2 = LineGraphSeries<DataPoint>(points2)


        // val series = LineGraphSeries(arrayOf(DataPoint(0, 1), DataPoint(1, 5), DataPoint(2, 3)))
        graph.addSeries(series1)
        series1.isDrawDataPoints()

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);


        var btn_hebdo = findViewById(R.id.hebdo) as Button
        var btn_mensu = findViewById(R.id.mensu) as Button
        btn_hebdo.setOnClickListener {
            graph.removeAllSeries();
            graph.addSeries(series1)

        }

        btn_mensu.setOnClickListener {
            graph.removeAllSeries();
            graph.addSeries(series2)
        }





        var btn_recueil = findViewById(R.id.btn1) as Button




        btn_recueil.setOnClickListener {




        }}
    fun DP(a: Int, b: Int): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }
    fun DP2(a: String, b: Int): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


}


