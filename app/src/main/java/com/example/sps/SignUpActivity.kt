package com.example.sps

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val file:String = "dataUser"
        val data:String = "wiss,wiss23,ben,wiss,21"
        val fileOutputStream: FileOutputStream
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



        var btnsignup = findViewById(R.id.signUpBtn) as Button
        var nu = findViewById(R.id.fullName) as EditText
        var nom = findViewById(R.id.noms) as EditText
        var pre = findViewById(R.id.prenoms) as EditText
        var phn = findViewById(R.id.nums) as EditText
        var pwd = findViewById(R.id.passwordd) as EditText
        var pwdc = findViewById(R.id.confirmPassword) as EditText
        var term=findViewById(R.id.terms)as CheckBox
        btnsignup.setOnClickListener {

            if((nu.text.isNotEmpty())&&(nom.text.isNotEmpty())&&(pre.text.isNotEmpty())&&(phn.text.isNotEmpty())&&(pwd.text.isNotEmpty())&&(pwdc.text.isNotEmpty())&&(term.isChecked)){

                if (pwd.text.toString().equals(pwdc.text.toString()))
                {
                    if(phn.text.length==10)
                    {


                        val newuser= User(nu.text.toString(),nom.text.toString(),pre.text.toString(),342,pwd.text.toString())
                        val intent = Intent(this, RecueilActivity::class.java)
                        intent.putExtra("usertype", "u")
                        startActivity(intent)
                       // println("fffhfghfggfhghhhfgffghgfghhfgfgfghfgfgffgfgffghfhffh"+ newuser.toString())
                        val data:String = newuser.toString()
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
                    else{
                        Toast.makeText(this@SignUpActivity, "Numero non valide", Toast.LENGTH_LONG).show()
                    }




                }
                else{
                    Toast.makeText(this@SignUpActivity, "Les deux mots de passe ne sont pas identiques", Toast.LENGTH_LONG).show()
                }






            }

else{

                Toast.makeText(this@SignUpActivity, "Veuillez remplir tous les champs  ", Toast.LENGTH_LONG).show()


            }
        }









    }
}