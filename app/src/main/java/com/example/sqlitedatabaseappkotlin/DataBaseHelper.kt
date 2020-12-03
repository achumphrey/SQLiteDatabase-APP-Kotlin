package com.example.sqlitedatabaseappkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context):
    SQLiteOpenHelper(context,
    DB_NAME,
    null,
    DB_VERSION) {

    companion object{
        //Database Table name
        const val TABLE_NAME = "Countries"

        //Table columns
        const val _ID = "_id"
        const val SUBJECT = "subject"
        const val DESC = "description"

        //Database name
        private const val DB_NAME = "COUNTRIES_CURRENCIES_DB"

        //Database version number
        private const val DB_VERSION = 1

        //Query statement to create the table
        const val CREATE_TABLE = "CREATE TABLE if not exists " +
                TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUBJECT + " TEXT NOT NULL, " +
                DESC + " TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}