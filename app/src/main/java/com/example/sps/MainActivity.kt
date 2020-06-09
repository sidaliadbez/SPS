package com.example.sps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


/**
 * A Login Form Example in Kotlin Android
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        var et_user_name = findViewById(R.id.username) as EditText
        var et_password = findViewById(R.id.password) as EditText
        var btn_reset = findViewById(R.id.signup) as Button
        var btn_submit = findViewById(R.id.login) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            // val user_name = et_user_name.text;
            //  val password = et_password.text;
            //  Toast.makeText(this@MainActivity, user_name, Toast.LENGTH_LONG).show()
            val intent = Intent(this, RecueilActivity::class.java)
            startActivity(intent);


            // your code to validate the user_name and password combination
            // and verify the same

        }
    }
}