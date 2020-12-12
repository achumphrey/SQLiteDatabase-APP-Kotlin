package com.example.sqlitedatabaseappkotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CountryListActivity : AppCompatActivity() {

    private  lateinit var dbManager: DataBaseManager
    private lateinit var listView: ListView
    private lateinit var adapter: SimpleCursorAdapter

    //Array of column titles in the cursor
    // which was retrieved from the database
    val from = arrayOf(
        DataBaseHelper.ID,
        DataBaseHelper.SUBJECT,
        DataBaseHelper.DESC
    )

    //Array of view components to bind the data
    // from the cursor in order to display them
    val to = intArrayOf(
        R.id.tvId,
        R.id.tvTitle,
        R.id.tvDesc
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_empty_list)

        dbManager = DataBaseManager(this)
        dbManager.open()
        val cursor = dbManager.fetch()

        listView = findViewById(R.id.list_view)
        listView.emptyView = findViewById(R.id.empty)

        adapter = SimpleCursorAdapter(
            this,
            R.layout.record_view_activity,
            cursor,
            from,
            to,
            0
        )
        adapter.notifyDataSetChanged()
        listView.adapter = adapter

        //OnclickListener for list items
        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val idTextView = view.findViewById<TextView>(R.id.tvId)
                val titleTextView = view.findViewById<TextView>(R.id.tvTitle)
                val descTextView = view.findViewById<TextView>(R.id.tvDesc)
                val itemId = idTextView.text.toString()
                val itemTitle = titleTextView.text.toString()
                val itemDesc = descTextView.text.toString()
                val modify_intent = Intent(
                    applicationContext,
                    ModifyCountryActivity::class.java
                )
                modify_intent.putExtra("id", itemId)
                modify_intent.putExtra("title", itemTitle)
                modify_intent.putExtra("desc", itemDesc)
                startActivity(modify_intent)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.add_record) {
            val add_mem = Intent(
                this@CountryListActivity,
                AddCountryActivity::class.java
            )
            startActivity(add_mem)
        }

        return super.onOptionsItemSelected(item)
    }
}