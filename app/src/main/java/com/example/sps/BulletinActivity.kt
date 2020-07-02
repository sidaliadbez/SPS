package com.example.sps

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sps.MainActivity.Companion.db
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_guerisonmort.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class BulletinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)


        supportActionBar?.title="Bulletins Épidémiologiques"

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
        var ages = ArrayList<String>()
        ages.add("0")
        ages.add("5-19")
        ages.add("20-29")
        ages.add("30-39")
        ages.add("40-49")
        ages.add("50-59")
        ages.add("60-69")
        ages.add("70+")
        barChart.xAxis.valueFormatter= IndexAxisValueFormatter(ages)
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
        barChart.legend.isEnabled= false
        barChart.data= data2







        var hbarChart : HorizontalBarChart = findViewById(R.id.hbarchart)
        hbarChart.setDrawBarShadow(false)
        hbarChart.setDrawValueAboveBar(true)

        hbarChart.setPinchZoom(false)
        hbarChart.setDrawGridBackground(false)
        val  leftAxish : YAxis= hbarChart.getAxisLeft();
        val rightAxish :YAxis = hbarChart.getAxisRight();
        val xxAxish:XAxis = hbarChart.getXAxis();
       // rightAxish.setEnabled(true);
       leftAxish.setEnabled(false);
        //xxAxish.setEnabled(false);
        rightAxish.labelCount=2
        xxAxish.labelCount=2
        var contry = ArrayList<String>()
        contry.add("algeria")
        contry.add("Homme")
        contry.add("Femme")
        xxAxish.setDrawAxisLine(false)
        xxAxish.setDrawGridLines(false)
        hbarChart.xAxis.valueFormatter= IndexAxisValueFormatter(contry)
        var hbarEntries = ArrayList<BarEntry>()
        hbarEntries= getbarentries3(hbarEntries)
        var hbarDataSet = BarDataSet(hbarEntries,"Total des cas")
        var listColorsh = ArrayList<Int>()

        listColorsh.add(Color.parseColor("#5AC7AA"))
        listColorsh.add(Color.parseColor("#F24E4E"))

        hbarDataSet.colors=listColorsh

        hbarDataSet.valueTextColor= Color.RED

        var data3 = BarData(hbarDataSet)
        data3.barWidth=0.5f
        data3.setValueTextColor(Color.RED)
        data3.setValueTextSize(20f)
        hbarChart.animateY(3000, Easing.EaseInOutBack)
        hbarChart.description.isEnabled = false
        hbarChart.legend.isEnabled= false
        hbarChart.data= data3






        var pieChart : PieChart = findViewById(R.id.piechart)
        var listPie = ArrayList<PieEntry>()
        listPie=getpieentries(listPie)
        var listColors = ArrayList<Int>()
        listColors.add(Color.parseColor("#026D0A"))
        listColors.add(Color.parseColor("#5AC7AA"))
        listColors.add(Color.parseColor("#F24E4E"))
        listColors.add(Color.parseColor("#EFBB12"))

        var pieDataSet = PieDataSet(listPie, "")
        pieDataSet.colors = listColors
        pieDataSet.valueTextColor=Color.BLACK

        var pieData = PieData(pieDataSet)
        pieData.setValueTextSize(0f)

        pieChart.data = pieData
        pieChart.invalidate()
        pieChart.setUsePercentValues(false)
        pieChart.isDrawHoleEnabled = true
        pieChart.description.isEnabled = false
        pieChart.setHoleColor((-0xFFF9F9F9).toInt())
        pieChart.animateY(2000, Easing.EaseInOutBack)


    }



    fun getbarentries(barEntries: ArrayList<BarEntry>):ArrayList<BarEntry>{
        var day1= 0
        var day2= 0
        var day3= 0
        var day4= 0
        var day5= 0
        var day6= 0
        var day7= 0
        val  list = db.readCas()
        list.sortWith(compareBy<cas> { it.type }.thenBy { it.date })
        var formate = SimpleDateFormat("dd MMM, yyyy", Locale.FRENCH)
        for (cas in list){
            if (cas.type==1){
                val currentTime = Calendar.getInstance().time
                val datedork = formate.format(currentTime.time)
                val datedork1= formate.parse(datedork)
                val date= formate.parse(cas.date)
                val betweendays = datedork1.time - date.time
                val nbdays =TimeUnit.DAYS.convert(betweendays, TimeUnit.MILLISECONDS)
                when(nbdays){
                    0L-> day1++
                    1L-> day2++
                    2L-> day3++
                    3L-> day4++
                    4L-> day5++
                    5L-> day6++
                    6L-> day7++
                    else->{

                    }
                }
            }
        }

        barEntries.add(BarEntry(1f,day7.toFloat()))
        barEntries.add(BarEntry(2f,day6.toFloat()))
        barEntries.add(BarEntry(3f,day5.toFloat()))
        barEntries.add(BarEntry(4f,day4.toFloat()))
        barEntries.add(BarEntry(5f,day3.toFloat()))
        barEntries.add(BarEntry(6f,day2.toFloat()))
        barEntries.add(BarEntry(7f,day1.toFloat()))


        return barEntries
    }
    fun lineentries(barEntries: ArrayList<Entry>):ArrayList<Entry>{
        var day1= 0
        var day2= 0
        var day3= 0
        var day4= 0
        var day5= 0
        var day6= 0
        var day7= 0
        val  list = db.readCas()
        list.sortWith(compareBy<cas> { it.type }.thenBy { it.date })
        var formate = SimpleDateFormat("dd MMM, yyyy", Locale.FRENCH)
        for (cas in list){
            if (cas.type==3){
                val currentTime = Calendar.getInstance().time
                val datedork = formate.format(currentTime.time)
                val datedork1= formate.parse(datedork)
                val date= formate.parse(cas.date)
                val betweendays = datedork1.time - date.time
                val nbdays =TimeUnit.DAYS.convert(betweendays, TimeUnit.MILLISECONDS)
                when(nbdays){
                    0L-> day1 += cas.caracteristique2.toInt()
                    1L-> day2+= cas.caracteristique2.toInt()
                    2L-> day3+= cas.caracteristique2.toInt()
                    3L-> day4+= cas.caracteristique2.toInt()
                    4L-> day5+= cas.caracteristique2.toInt()
                    5L-> day6+= cas.caracteristique2.toInt()
                    6L-> day7+= cas.caracteristique2.toInt()
                    else->{

                    }
                }
            }
        }

        barEntries.add(Entry(1f,day7.toFloat()))
        barEntries.add(Entry(2f,day6.toFloat()))
        barEntries.add(Entry(3f,day5.toFloat()))
        barEntries.add(Entry(4f,day4.toFloat()))
        barEntries.add(Entry(5f,day3.toFloat()))
        barEntries.add(Entry(6f,day2.toFloat()))
        barEntries.add(Entry(7f,day1.toFloat()))



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

        val list= db.readCas()
        var one :Int= 0
        var two :Int= 0
        var three :Int= 0
        var four :Int= 0
        var five :Int= 0
        var six :Int= 0
        var seven :Int= 0

        list.forEach{
            if(it.type==1){
                when(it.caracteristique2.toFloat()){
                   in 0F..19F ->{
                        one++
                    }
                    in 20F..29F ->{
                        two++
                    }
                    in 30F..39F ->{
                        three++
                    }
                    in 40F..49F ->{
                        four++
                    }
                    in 50F..59F ->{
                        five++
                    }
                    in 60F..69F ->{
                        six++
                    }
                    in 70F..110F ->{
                        seven++
                    }
                }
            }

        }
        barEntries.add(BarEntry(1f,one.toFloat()))
        barEntries.add(BarEntry(2f,two.toFloat()))
        barEntries.add(BarEntry(3f,three.toFloat()))
        barEntries.add(BarEntry(4f,four.toFloat()))
        barEntries.add(BarEntry(5f,five.toFloat()))
        barEntries.add(BarEntry(6f,six.toFloat()))
        barEntries.add(BarEntry(7f,seven.toFloat()))


        return barEntries
    }
    fun getbarentries3(barEntries: ArrayList<BarEntry>):ArrayList<BarEntry>{
        val list= db.readCas()
        var h :Int= 0
        var f :Int= 0
        list.forEach{
            if(it.type==1){
                if (it.caracteristique1=="Male" || it.caracteristique1=="Homme"){
                    h++
                }else f++
            }

        }
        barEntries.add(BarEntry(1f,h.toFloat()))
        barEntries.add(BarEntry(2f,f.toFloat()))

        return barEntries
    }
    fun getpieentries(barEntries: ArrayList<PieEntry>):ArrayList<PieEntry>{
        val list= db.readWilaya()
        val nbcastotal = list[0].nbcas+list[1].nbcas+list[2].nbcas+list[3].nbcas

        val bejaia : Float = ((list[0].nbcas*100)/nbcastotal).toFloat()
        val annaba : Float = ((list[1].nbcas*100)/nbcastotal).toFloat()
        val alger : Float = ((list[2].nbcas*100)/nbcastotal).toFloat()
        val oran : Float = 100-(bejaia+annaba+alger)
        barEntries.add(PieEntry(bejaia, "Bejaia $bejaia"+
                "%"))
        barEntries.add(PieEntry(annaba,"Annaba $annaba"+
                "%"))
        barEntries.add(PieEntry(alger,"Alger $alger"+
                "%"))
        barEntries.add(PieEntry(oran,"Oran $oran " +
                "%"))

        return barEntries
    }
}
