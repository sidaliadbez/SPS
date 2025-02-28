package com.example.sps

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sps.MainActivity.Companion.db
import com.google.android.material.navigation.NavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_recueil_admin.*
import kotlinx.android.synthetic.main.activity_recueil_user.*
import kotlinx.android.synthetic.main.activity_recueil_user.btn1
import kotlinx.android.synthetic.main.activity_recueil_user.btn2
import kotlinx.android.synthetic.main.activity_recueil_user.btn3
import kotlinx.android.synthetic.main.activity_recueil_user.btn4
import kotlinx.android.synthetic.main.recueil.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class RecueilActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var strr: String? = ""

        val intent = intent
        if (intent != null){
            // Le code pour récupérer les extras ira ici
            var str: String? = ""
            if (intent.hasExtra("usertype")) { // vérifie qu'une valeur est associée à la clé “usertype”
                str = intent.getStringExtra("usertype") // on récupère la valeur associée à la clé
  strr=str
                if (str=="a"){ setContentView(R.layout.activity_recueil_admin)}
                if (str=="u"){ setContentView(R.layout.activity_recueil_user)}
            }

        }



        val filename ="dataUser"
        if( filename.toString().trim()!=""){
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput(filename)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
                Toast.makeText(this@RecueilActivity,stringBuilder.toString() , Toast.LENGTH_LONG).show()
            }
            //Displaying data on EditText
           // fileData.setText(stringBuilder.toString()).toString()

        }














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




        btn1.setOnClickListener {

        }
        btn2.setOnClickListener {
            val intent = Intent(this,MapActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this,UrgenceActivity::class.java)
            startActivity(intent)
        }
        btn4.setOnClickListener {
            val intent = Intent(this,BulletinActivity::class.java)
            startActivity(intent)
        }



        if (strr=="a"){

        btn5.setOnClickListener {
          val intent = Intent(this,MettreAjourCasActivity::class.java)
            startActivity(intent)
        }}
        val graph = findViewById(R.id.graph) as GraphView
        //val graph2 = findViewById(R.id.graph) as GraphView



        var list: MutableList<String> = ArrayList()
        var list2: MutableList<String> = ArrayList()


        val inputStream: InputStream = resources.openRawResource(R.raw.data_month)
        val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
        reader.readLines().forEach {
            val cols = it.split(",".toRegex()).toTypedArray()
        list.add(cols[1])

        }

        val inputStream2: InputStream = resources.openRawResource(R.raw.data_week)
        val readerr = BufferedReader(InputStreamReader(inputStream2, Charset.forName("UTF-8")))
        readerr.readLines().forEach {
            val colss = it.split(",".toRegex()).toTypedArray()
            list2.add(colss[1])


        }


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)
       println("Current Date and Time is: $formatted")

        var casac =""
        var casdec=""
        var casger=""
        var before =""
        var before2=""
        var before3=""
        var j =""
        var k=""
        var l=""
        val inputStream3: InputStream = resources.openRawResource(R.raw.data_day)
        val readerrr = BufferedReader(InputStreamReader(inputStream3, Charset.forName("UTF-8")))

        readerrr.readLines().forEach {
            val col = it.split(",".toRegex()).toTypedArray()

            if ((formatted.toString().equals(col[0]))) {
                casac=col[1]
                casdec=col[3]
                casger=col[2]
                before=j
                before2=l
                before3=k
            }
            else{

                j=col[1].toString()
                k=col[3].toString()
               l=col[2].toString()
            }
        }

     /*   var sum1=casac.toInt()-before.toInt()
        var sum2=casger.toInt()-before2.toInt()
        var sum3=casdec.toInt()-before3.toInt()*/





                          // Mis a jour
        val textView = findViewById<TextView>(R. id.actif)
        val textView2 = findViewById<TextView>(R. id.ger)
        val textView3 = findViewById<TextView>(R. id.dead)
        val textView4 = findViewById<TextView>(R. id.date)
        val textView5 = findViewById<TextView>(R. id.actiff)
        val textView6 = findViewById<TextView>(R. id.actifplus)
        val textView7 = findViewById<TextView>(R. id.gerplus)
        val textView8 = findViewById<TextView>(R. id.deadplus)
        var textView9 = findViewById<TextView>(R. id.plusactif)

        textView. setText(casac). toString()
        val textViewValue = textView. text
        textView2. setText(" "+casger). toString()
        val textViewValue2 = textView2. text
        textView3. setText(" "+casdec). toString()
        val textViewValue3 = textView3. text
        textView5. setText(casac+" "). toString()
        val textViewValue5 = textView5. text





/*
        if(sum1>0){
        textView6. setText(" [+"+sum1.toString()+"]"). toString()
            textView9.setText(textView6.text.toString())
        }
        if(sum2>0){
        textView7. setText(" [+"+sum2.toString()+"]"). toString()}
        if(sum3>0){
        textView8. setText(" [+"+sum3.toString()+"]"). toString()}

        if(sum1<0){
            textView6. setText(" ["+sum1.toString()+"]"). toString()
            textView9.setText(textView6.text.toString())}
        if(sum2<0){
            textView7. setText(" ["+sum2.toString()+"]"). toString()}
        if(sum3<0){
            textView8. setText(" ["+sum3.toString()+"]"). toString()}
*/













        val current2 = LocalDateTime.now()
        val formatterr = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val formattedd = current2.format(formatterr)
       // println("Current Date and Time is: $formattedd")

        textView4. setText("Dernière. màj : "+formattedd). toString()
        val textViewValue4 = textView4. text



        var btn_ajour = findViewById(R.id.update) as Button
        btn_ajour.setOnClickListener {
            val listWilaya= db.readWilaya()
            var nbcasactif= 0
            listWilaya.forEach {
                nbcasactif+= it.nbcas
            }
            actif.text=nbcasactif.toString()

            val listcas= db.readCas()
            var guerison = 0
            var morts= 0
            listcas.forEach {
                if (it.type==2){
                    guerison++
                }
                if (it.type==3){
                    morts++
                }
            }
            ger.text= guerison.toString()
            dead.text=morts.toString()

            var formate = SimpleDateFormat("dd MMM, yyyy", Locale.FRENCH)

            val currentTime = Calendar.getInstance().time
            val datedork = formate.format(currentTime.time)
            val datedork1= formate.parse(datedork)
            var newcas= 0
            var newguerison =0
            var newmorts=0
            listcas.forEach {
                var date = formate.parse(it.date)
                if(datedork1.compareTo(date)==0){
                    when(it.type){
                        1->newcas++
                        2->newguerison++
                        3->newmorts++
                    }
                }
            }
            actifplus.text= "[+"+newcas.toString()+"]"
            gerplus.text= "[+"+newguerison.toString()+"]"
            deadplus.text= "[+"+newmorts.toString()+"]"

        }


               var ints = list.map { it.toInt() }.toTypedArray()
               var intss = list2.map { it.toInt() }.toTypedArray()

        val points = arrayOf( DP(0,0),
            DP(1,intss[0]),
            DP(2,intss[1]),
            DP(3,intss[2]) ,
            DP(4,intss[3]),
            DP(5,intss[4]),
            DP(6,intss[5]),
            DP(7,intss[6]))

        val points2 = arrayOf( DP(0,0),
            DP(1,ints[0]),
            DP(2,ints[1]),
            DP(3,ints[2]) ,
            DP(4,ints[3]),
            DP(5,ints[4]),
            DP(6,ints[5]) ,
            DP(7,ints[6]),
            DP(8,ints[7]),
            DP(9,ints[8]),
            DP(10,ints[9]) ,
            DP(11,ints[10]),
            DP(12,ints[11])

        )



        val series1 = LineGraphSeries<DataPoint>(points)
        val series2 = LineGraphSeries<DataPoint>(points2)


        // val series = LineGraphSeries(arrayOf(DataPoint(0, 1), DataPoint(1, 5), DataPoint(2, 3)))
        graph.addSeries(series1)
        series1.isDrawDataPoints()

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
      //  graph.getViewport().setScalableY(true);
      //  graph.getViewport().setScrollableY(true);


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



        //Buttons for The menu
val listWilaya= db.readWilaya()
        var nbcasactif= 0
        listWilaya.forEach {
            nbcasactif+= it.nbcas
        }
actif.text=nbcasactif.toString()

        val listcas= db.readCas()
        var guerison = 0
        var morts= 0
        listcas.forEach {
            if (it.type==2){
                guerison++
            }
            if (it.type==3){
                morts++
            }
        }
        ger.text= guerison.toString()
        dead.text=morts.toString()

        var formate = SimpleDateFormat("dd MMM, yyyy", Locale.FRENCH)

                val currentTime = Calendar.getInstance().time
                val datedork = formate.format(currentTime.time)
                val datedork1= formate.parse(datedork)
        var newcas= 0
        var newguerison =0
        var newmorts=0
        listcas.forEach {
            var date = formate.parse(it.date)
                if(datedork1.compareTo(date)==0){
                    when(it.type){
                        1->newcas++
                        2->newguerison++
                        3->newmorts++
                    }
                }
        }
        actifplus.text= "[+"+newcas.toString()+"]"
        gerplus.text= "[+"+newguerison.toString()+"]"
        deadplus.text= "[+"+newmorts.toString()+"]"

    }









    fun DP(a: Int, b: Int): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }
    fun DP2(a: String, b: Int): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }
    fun DP3(a: String, b: String): DataPoint {
        return DataPoint(a.toDouble(), b.toDouble())
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


}


