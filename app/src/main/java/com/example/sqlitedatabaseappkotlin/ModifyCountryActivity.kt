package com.example.sqlitedatabaseappkotlin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ModifyCountryActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var edTitle: EditText
    private lateinit var edDesc: EditText
    private lateinit var btUpdate: Button
    private lateinit var btDelete: Button
    private var itemId: Long = 0
    private lateinit var dbManager: DataBaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_record)

        title = "Modify Record"

        dbManager = DataBaseManager(this)
        dbManager.open()

        edTitle = findViewById(R.id.subEdit)
        edDesc = findViewById(R.id.descEdit)

        btUpdate = findViewById(R.id.btUpdate)
        btDelete = findViewById(R.id.btDelete)

        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")

        //cast string to long
        itemId = id!!.toLong()

        edTitle.setText(name)
        edDesc.setText(desc)

        btUpdate.setOnClickListener(this)
        btDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btUpdate -> {
                val title: String = edTitle.text.toString()
                val desc: String = edDesc.text.toString()
                dbManager.update(itemId, title, desc)
                this.returnHome()
            }
            R.id.btDelete -> {
                dbManager.delete(itemId)
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
