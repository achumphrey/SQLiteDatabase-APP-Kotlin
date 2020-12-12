package com.example.sqlitedatabaseappkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddCountryActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var btAdd: Button
    private lateinit var edSub: EditText
    private lateinit var edDesc:EditText
    private lateinit var dbManager: DataBaseManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        title = "Add Record"

        edSub = findViewById(R.id.edSubject)
        edDesc = findViewById(R.id.edDescription)
        btAdd = findViewById(R.id.btAdd)

        dbManager = DataBaseManager(this)
        dbManager.open()
        btAdd.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btAdd -> {
                val name = edSub.text.toString()
                val desc = edDesc.text.toString()
                dbManager.insert(name, desc)
                this.returnHome()
            }
        }
    }

    private fun returnHome() {
        val homeIntent = Intent(
            applicationContext,
            CountryListActivity::class.java
        )
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(homeIntent)
    }
}
