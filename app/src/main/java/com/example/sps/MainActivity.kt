package com.example.sps

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent= Intent(this,
                BulletinActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent= Intent(this,
                UrgenceActivity::class.java)
            startActivity(intent)
        }


    }
}
