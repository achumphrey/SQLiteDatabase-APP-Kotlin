package com.example.sqlitedatabaseappkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Often a SQLite database will be used across your entire application;
 * within services, applications, fragments, and more. For this reason,
 * best practices often advise you to apply the singleton pattern to your
 * SQLiteOpenHelper instances to avoid memory leaks and unnecessary
 * re-allocations. The best solution is to make your database instance a
 * singleton instance across the entire application's lifecycle.
 */

/**
 * Constructor should be private to prevent direct instantiation.
 * Make a call to the static method "getInstance()" instead.
 */

class DataBaseHelper private constructor(context: Context):
    SQLiteOpenHelper(context,
    DB_NAME,
    null,
    DB_VERSION) {

    companion object{
        //Instance of this class
        private var mInstance: DataBaseHelper? = null

        //Instantiate the class object
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        @Synchronized
        fun getInstance(context: Context): DataBaseHelper{

            if(mInstance == null)
                mInstance = DataBaseHelper(context.applicationContext)

            return mInstance as DataBaseHelper
        }

        //Database Table name
        const val TABLE_NAME = "Countries"

        //Table columns
        const val ID = "_id"
        const val SUBJECT = "subject"
        const val DESC = "description"

        //Database name
        private const val DB_NAME = "COUNTRIES_CURRENCIES_DB"

        //Database version number
        private const val DB_VERSION = 1

        //Query statement to create the table
        const val CREATE_TABLE = "CREATE TABLE if not exists " +
                TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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