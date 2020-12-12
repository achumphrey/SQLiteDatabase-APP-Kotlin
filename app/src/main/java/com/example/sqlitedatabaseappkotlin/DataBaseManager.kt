package com.example.sqlitedatabaseappkotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

class DataBaseManager (private val context: Context) {

    private lateinit var dbHelper: DataBaseHelper
    private lateinit var database: SQLiteDatabase

    //Method to open a writable database connection
    @Throws(SQLException::class)
    fun open(): DataBaseManager {
        dbHelper = DataBaseHelper.getInstance(context)
        database = dbHelper.writableDatabase

        return this
    }

    //Method to close database connection
    private fun close() {
        dbHelper.close()
    }

    //Method to insert data into the database table
    fun insert(name: String?, desc: String?) {

        //The ContentValues class holds the data to be inserted
        val contentValues = ContentValues()
        contentValues.put(DataBaseHelper.SUBJECT, name)
        contentValues.put(DataBaseHelper.DESC, desc)
        database.insert(
            DataBaseHelper.TABLE_NAME,
            null,
            contentValues
        )
        close()
    }

    //Method to update records in the database
    fun update(_id: Long, name: String?, desc: String?): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseHelper.SUBJECT, name)
        contentValues.put(DataBaseHelper.DESC, desc)
        val i = database.update(
            DataBaseHelper.TABLE_NAME,
            contentValues,
            DataBaseHelper.ID + " = " + _id,
            null
        )
        close()
        return i
    }

    //Method to delete a record from the database
    fun delete(_id: Long) {
        database.delete(
            DataBaseHelper.TABLE_NAME,
            DataBaseHelper.ID + " = " + _id,
            null
        )
        close()
    }

    //Method to fetch data from the database table
    fun fetch(): Cursor? {

        //create a string array of column titles
        // whose data you want to retrieve
        val columns = arrayOf(
            DataBaseHelper.ID,
            DataBaseHelper.SUBJECT,
            DataBaseHelper.DESC
        )

        //Retrieve the data into the cursor object
        val cursor = database.query(
            DataBaseHelper.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            DataBaseHelper.SUBJECT + " DESC" //ASC for ascending order
        )
        cursor?.moveToFirst()
        close()
        return cursor
    }

}