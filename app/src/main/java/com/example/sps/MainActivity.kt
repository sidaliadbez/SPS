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
    companion object{
        lateinit var db : MindOrksDBOpenHelper
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db =
            MindOrksDBOpenHelper(this, null)

                var text :String=""

 val  list  = db.readWilaya()

      //  list.sortWith(compareBy<cas> { it.type }.thenBy { it.date })

        list.forEach{
            text = text + "---"+" "+it.nom +" "//+ it.date+" "
        }
        dbtext.text=text
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
button3.setOnClickListener {
    val intent= Intent(this,
        MapActivity::class.java)
    startActivity(intent)
}
        button4.setOnClickListener {
            val intent= Intent(this,
                MettreAjourCasActivity::class.java)
            startActivity(intent)
        }

    }
}
