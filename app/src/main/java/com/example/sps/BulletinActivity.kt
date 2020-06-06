package com.example.sps

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*


class BulletinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        var combinedchart : CombinedChart = findViewById(R.id.combinedchart)
        combinedchart.setDrawBarShadow(false)
        combinedchart.setDrawValueAboveBar(true)
        combinedchart.setPinchZoom(false)
        combinedchart.setDrawGridBackground(false)

        ////////////Enteries
        var barentries = ArrayList<BarEntry>()
        barentries= getbarentries(barentries)
        var lineentries = ArrayList<Entry>()
        lineentries= lineentries(lineentries)


        ////////////DataLines-bar//////////////////////////

        var dataline = LineData()
        var linedataset = LineDataSet(lineentries,"Total Décédés")
        linedataset=linedataset(linedataset)
        linedataset.color=Color.RED
        dataline.addDataSet(linedataset)



        var databar = BarData()
        var bardataset = BarDataSet(barentries,"Total confirmés")
        bardataset= baredataset(bardataset)

                bardataset.color=Color.parseColor("#5AC7AA")
        databar.addDataSet(bardataset)
        val xAxis: XAxis = combinedchart.getXAxis();
        xAxis.isEnabled= false
        val yaxisl :YAxis= combinedchart.axisLeft
        yaxisl.isEnabled= false
        val yaxisr :YAxis= combinedchart.axisRight
        yaxisr.isEnabled= false
        yaxisl.isEnabled= false

        ////////////SetData//////////////////////
        combinedchart.description.isEnabled = false
        var data: CombinedData = CombinedData()
        data.setData(dataline)
        data.setData(databar)
        data.barData.barWidth=0.5f
        data.setValueTextColor(Color.BLACK)
       // xAxis.setAxisMaximum(data.getXMax() + 0.5f);
        xAxis.labelCount=4
        combinedchart.data= data
        combinedchart.invalidate()








        var barChart : BarChart = findViewById(R.id.barchart)
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.setMaxVisibleValueCount(300)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)
        val  leftAxis : YAxis= barChart.getAxisLeft();
        val rightAxis :YAxis = barChart.getAxisRight();
        val xxAxis:XAxis = barChart.getXAxis();
        rightAxis.setEnabled(false);
        leftAxis.setEnabled(false);
        xxAxis.setEnabled(true);

        var barEntries = ArrayList<BarEntry>()
        barEntries= getbarentries2(barEntries)
        var barDataSet = BarDataSet(barEntries,"Total des cas")
        barDataSet.color=  Color.parseColor("#5AC7AA")
        var data2 = BarData(barDataSet)
        data2.barWidth=0.5f
        data2.setValueTextColor(Color.BLACK)
        data2.setValueTextSize(10f)
        barChart.animateY(3000, Easing.EaseInOutBack)
        barChart.description.isEnabled = false
        barChart.data= data2
    }



    fun getbarentries(barEntries: ArrayList<BarEntry>):ArrayList<BarEntry>{
        barEntries.add(BarEntry(1f,30F))
        barEntries.add(BarEntry(2f,20F))
        barEntries.add(BarEntry(3f,15F))
        barEntries.add(BarEntry(4f,40F))
        barEntries.add(BarEntry(5f,30F))
        barEntries.add(BarEntry(6f,20F))
        barEntries.add(BarEntry(7f,20F))


        return barEntries
    }
    fun lineentries(barEntries: ArrayList<Entry>):ArrayList<Entry>{
        barEntries.add(Entry(1f,20F))
        barEntries.add(Entry(2f,10F))
        barEntries.add(Entry(3f,8F))
        barEntries.add(Entry(4f,35F))
        barEntries.add(BarEntry(5f,25F))
        barEntries.add(BarEntry(6f,15f))
        barEntries.add(BarEntry(7f,13F))

        return barEntries
    }

    fun  linedataset (set: LineDataSet): LineDataSet {
        set.setColor(Color.rgb(240, 238, 70));
        set.setColors(Color.YELLOW);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.RED);
        set.setCircleRadius(5f);
        set.setFillColor(Color.YELLOW);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
       // set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return  set
    }
    fun  baredataset (set: BarDataSet): BarDataSet {
        set.setColor(Color.rgb(60, 220, 78));
        set.setColors(Color.RED)
        //set.setValueTextColor(Color.rgb(60, 220, 78));
        set.setValueTextSize(10f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return  set
    }


    fun getbarentries2(barEntries: ArrayList<BarEntry>):ArrayList<BarEntry>{
        barEntries.add(BarEntry(1f,30F))
        barEntries.add(BarEntry(2f,20F))
        barEntries.add(BarEntry(3f,15F))
        barEntries.add(BarEntry(4f,40F))
        barEntries.add(BarEntry(5f,30F))
        barEntries.add(BarEntry(6f,20F))
        barEntries.add(BarEntry(7f,20F))


        return barEntries
    }
}
