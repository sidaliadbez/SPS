package com.example.sps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

/*
        val filename = "dataUser"
        if(filename.toString()!=null && filename.toString().trim()!="") {
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput(filename)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
          if ({ text = bufferedReader.readLine(); text }() == null) {
              val data:String = "wiss,wiss23,ben,wiss,21"
              var file:String = "dataUser"
              val fileOutputStream:FileOutputStream
              try {
                  fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                  fileOutputStream.write(data.toByteArray())

              } catch (e: FileNotFoundException){
                  e.printStackTrace()
              }catch (e: NumberFormatException){
                  e.printStackTrace()
              }catch (e: IOException){
                  e.printStackTrace()
              }catch (e: Exception){
                  e.printStackTrace()
              }
            }
*/




        Handler().postDelayed({


            startActivity(Intent(this,MainActivity::class.java))


            finish()
        }, SPLASH_TIME_OUT)
    }
}