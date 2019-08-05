package com.example.rueda.s2next.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME = "S2NEXT"
val TABLE_NAME = "customer"
val COL_ID = "id"
val COL_NAME = "name"
val COL_MIDDLE_NAME = "M_NAME"
val COL_LAST_NAME = "L_NAME"
val SECOND_LAST_NAME = "S_NAME"
val BIRTHDATE = "CUMPLE"
val GENDER= "GENERO"



class DataBaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableCustomer = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME + " TEXT , " + COL_MIDDLE_NAME + " TEXT , " + COL_LAST_NAME + " TEXT , " +
                SECOND_LAST_NAME + " TEXT , " + BIRTHDATE + " DATE , " + GENDER + " INT)"
        //Log.i("insert", createTableCustomer)
        db?.execSQL(createTableCustomer)

    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertCustomer(user: user){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_MIDDLE_NAME, user.middle_name)
        cv.put(COL_LAST_NAME, user.last_name)
        cv.put(SECOND_LAST_NAME, user.second_name)
        cv.put(BIRTHDATE, user.birthdate)
        cv.put(GENDER, user.gender)

        Log.i("DB", cv.toString())

        var result = db.insert(TABLE_NAME, null, cv)
        if(result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
        }
    }
    fun readData(): MutableList <user>{

        var list : MutableList <user> = ArrayList()

        var db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var u = user()
                u.id = result.getInt(result.getColumnIndex(COL_ID))
                u.name = result.getString(result.getColumnIndex(COL_NAME))
                u.middle_name = result.getString(result.getColumnIndex(COL_MIDDLE_NAME))
                u.last_name = result.getString(result.getColumnIndex(COL_LAST_NAME))
                u.second_name = result.getString(result.getColumnIndex(SECOND_LAST_NAME))
                u.birthdate = result.getString(result.getColumnIndex(BIRTHDATE))
                u.gender = result.getInt(result.getColumnIndex(GENDER))
                list.add(u)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun editar_usuario(id_editar: Int): MutableList<user>{

        var list : MutableList <user> = ArrayList()
        var db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id_editar
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var u = user()
                u.id = result.getInt(result.getColumnIndex(COL_ID))
                u.name = result.getString(result.getColumnIndex(COL_NAME))
                u.middle_name = result.getString(result.getColumnIndex(COL_MIDDLE_NAME))
                u.last_name = result.getString(result.getColumnIndex(COL_LAST_NAME))
                u.second_name = result.getString(result.getColumnIndex(SECOND_LAST_NAME))
                u.birthdate = result.getString(result.getColumnIndex(BIRTHDATE))
                //u.gender = result.getInt(result.getColumnIndex(GENDER))
                list.add(u)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list

    }
    fun update_usuario(){

    }

}

