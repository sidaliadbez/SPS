package com.example.sps


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

import android.content.Context

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.io.*
import java.nio.charset.Charset




class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var db : MindOrksDBOpenHelper
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db =
            MindOrksDBOpenHelper(this, null)





        // get reference to all views
        var et_user_name = findViewById(R.id.username) as EditText
        var et_password = findViewById(R.id.password) as EditText
        var btn_signup = findViewById(R.id.signup) as Button
        var btn_submit = findViewById(R.id.login) as Button

        btn_signup.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")




           val intent = Intent(this, SignUpActivity::class.java)
           startActivity(intent)


        }

        // set on-click listener
        btn_submit.setOnClickListener {
            // val user_name = et_user_name.text;
            //  val password = et_password.text;
            //  Toast.makeText(this@MainActivity, user_name, Toast.LENGTH_LONG).show()

            //val inputStream: InputStream = resources.openRawResource(R.raw.data)
           // val reader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))

             var i=0
            var find=false
            var choix=0



            val filename = "dataUser"
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)


                }

                val cols = stringBuilder.split(",".toRegex()).toTypedArray()

                // val cols = it.split(",".toRegex()).toTypedArray()
                i=i+1
                if ((et_user_name.text.toString().equals(cols[0]))&&(et_password.text.toString().equals(cols[1]))){

                    println("YESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS")
                    find=true
                    choix=i
                   if(et_user_name.text.toString().equals("wiss")){ choix=2}
                    println("find="+find)
                    println("wel i "+choix)
                }

                //Displaying data on EditText
                //println("Coulmn 0 = '" + cols[0] + "', Column 1 = '" + cols[1] + "', Column 2: '" + cols[2] + "'")
               // fileData.setText(stringBuilder.toString()).toString()
            }


     // ACCESS TO ACCOUNT
           if (find==true) {


    val intent = Intent(this, RecueilActivity::class.java)
              var choixx: String? =""
           if (choix==2){choixx="a"}
           else{choixx="u"}

    intent.putExtra("usertype", choixx)
    startActivity(intent)



}


  // AUTHENTIFICATION (ECHEC)
            if (find==false) {

                Toast.makeText(this@MainActivity, "Nom d'utilisateur ou mdp incorrect ", Toast.LENGTH_LONG).show()
            }


        }
    }

}