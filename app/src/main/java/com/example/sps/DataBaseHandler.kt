package com.example.sps

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList




class MindOrksDBOpenHelper(context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,
        DATABASE_NAME,
        factory,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COL_AGE + " TEXT,"+ COLUMN_DESCRIPTION +" TEXT)"
        val CREATE_PRODUCTS_TABLECAS = "CREATE TABLE " + TABLE_CAS + "(" + COLUMN_IDCAS + " INTEGER PRIMARY KEY,"  + COLUMN_WILAYA+ " TEXT,"+ COLUMN_TYPE+ " TEXT," +COLUMN_CARACT1 + " TEXT,"+ COLUMN_CARACT2+ " TEXT,"+ COLUMN_TIME + " TEXT," + COLUMN_DATE+" TEXT)"
        val CREATE_PRODUCTS_TABLEWILAYA = "CREATE TABLE " + TABLE_WILAYA + "(" + COLUMN_IDWILAYA + " INTEGER PRIMARY KEY,"  + COLUMN_NBCAS+ " TEXT,"+ COLUMN_NOMWILAYA+ " TEXT," + COLUMN_LAG + " TEXT,"+  COLUMN_LNG+" TEXT)"

        db.execSQL(CREATE_PRODUCTS_TABLEWILAYA)
        db.execSQL(CREATE_PRODUCTS_TABLECAS)
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAS)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WILAYA)
        onCreate(db)
    }
    fun addData(note: Urgence) {
        val values = ContentValues()
        values.put(COLUMN_NAME, note.nom)
        values.put(COLUMN_DESCRIPTION, note.contenu)
        values.put(COL_AGE, note.phone)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        Log.i("###################","/////////////////////////")
        db.close()
    }
    fun addCas(note: cas) {
        val values = ContentValues()
        values.put(COLUMN_TYPE, note.type)
        values.put(COLUMN_WILAYA, note.wilaya)
        values.put(COLUMN_CARACT1, note.caracteristique1)
        values.put(COLUMN_CARACT2, note.caracteristique2)
        values.put(COLUMN_TIME, note.time)
        values.put(COLUMN_DATE, note.date)
        val db = this.writableDatabase
        db.insert(TABLE_CAS, null, values)
        Log.i("###################","/////////////////////////")
        db.close()
    }
    fun addWilaya(note: Wilaya) {
        val values = ContentValues()
        values.put(COLUMN_NOMWILAYA, note.nom)
        values.put(COLUMN_NBCAS, note.nbcas)
        values.put(COLUMN_LAG, note.lag)
        values.put(COLUMN_LNG, note.lng)
        val db = this.writableDatabase
        db.insert(TABLE_WILAYA, null, values)
        Log.i("###################","/////////////////////////")
        db.close()
    }
    fun readData() : MutableList<Urgence>{
        var list : MutableList<Urgence> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var     note = Urgence()
                note.id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                note.nom = result.getString(result.getColumnIndex(COLUMN_NAME))
                note.contenu = result.getString(result.getColumnIndex(COLUMN_DESCRIPTION))
                note.phone = result.getString(result.getColumnIndex(COL_AGE))
                list.add(note)
            }while (result.moveToNext())
        }


        result.close()
        db.close()
        return list
    }

    fun readCas() : MutableList<cas>{
        var list : MutableList<cas> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_CAS"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var     note = cas()
                note.id = result.getString(result.getColumnIndex(COLUMN_IDCAS)).toInt()
                note.type = result.getString(result.getColumnIndex(COLUMN_TYPE)).toInt()
                note.wilaya = result.getString(result.getColumnIndex(COLUMN_WILAYA))
                note.caracteristique1 = result.getString(result.getColumnIndex(COLUMN_CARACT1))
                note.caracteristique2 = result.getString(result.getColumnIndex(COLUMN_CARACT2))
                note.time = result.getString(result.getColumnIndex(COLUMN_TIME))
                note.date = result.getString(result.getColumnIndex(COLUMN_DATE))
                list.add(note)
            }while (result.moveToNext())
        }


        result.close()
        db.close()
        return list
    }


    fun readWilaya() : MutableList<Wilaya>{
        var list : MutableList<Wilaya> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_WILAYA"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var     note = Wilaya()
                note.id = result.getString(result.getColumnIndex(COLUMN_IDWILAYA)).toInt()
                note.nom = result.getString(result.getColumnIndex(COLUMN_NOMWILAYA))
                note.nbcas = result.getString(result.getColumnIndex(COLUMN_NBCAS)).toInt()
                note.lng = result.getString(result.getColumnIndex(COLUMN_LNG)).toDouble()
                note.lag = result.getString(result.getColumnIndex(COLUMN_LAG)).toDouble()
                list.add(note)
            }while (result.moveToNext())
        }


        result.close()
        db.close()
        return list
    }
    fun deleteData(note: Urgence){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"_id=?", arrayOf(note.id.toString()))
        db.close()
    }

    fun deleteCas(note: cas){
        val db = this.writableDatabase
        db.delete(TABLE_CAS,"idcas=?", arrayOf(note.id.toString()))
        db.close()
    }
    fun updateData(id: String, name:String, description: String,phone: String):Boolean{
        val values = ContentValues()
        val db = this.writableDatabase
        var result:Boolean = false
        values.put(COLUMN_NAME,name)
        values.put(COLUMN_DESCRIPTION,description)
        values.put(COL_AGE,phone)
        try {
            db.update(TABLE_NAME,values,"$COLUMN_ID=?", arrayOf(id))
            result=true
        }catch (e:Exception){
            result=false
        }

        return result
    }

    fun updateWilaya(id: String, name:String, nbcas: Int,lag: Double,lng:Double):Boolean{
        val values = ContentValues()
        val db = this.writableDatabase
        var result:Boolean = false
        values.put(COLUMN_NOMWILAYA,name)
        values.put(COLUMN_NBCAS,nbcas)
        values.put(COLUMN_LAG,lag)
        values.put(COLUMN_LNG,lng)
        try {
            db.update(TABLE_WILAYA,values,"$COLUMN_IDWILAYA=?", arrayOf(id))
            result=true
        }catch (e:Exception){
            result=false
        }

        return result
    }

    companion object {
        private val DATABASE_VERSION = 6
        private val DATABASE_NAME = "mydb"
        val TABLE_NAME = "urgence"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "nom"
        val COLUMN_DESCRIPTION = "description"
        val COL_AGE = "phone"

        val TABLE_CAS = "cas"
        val COLUMN_TYPE = "type"
        val COLUMN_IDCAS = "idcas"
        val COLUMN_WILAYA = "wilaya"
        val COLUMN_CARACT1 = "caract1"
        val COLUMN_CARACT2 = "caract2"
        val COLUMN_TIME= "time"
        val COLUMN_DATE = "date"


        val TABLE_WILAYA = "wilaya"
        val COLUMN_NOMWILAYA = "nomwilaya"
        val COLUMN_IDWILAYA = "idwilaya"
        val COLUMN_NBCAS = "nbcas"
        val COLUMN_LAG = "lag"
        val COLUMN_LNG = "lng"

    }
}