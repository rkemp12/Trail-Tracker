package com.rkemp12.trailtracker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val myDataset = Datasource().loadTrails()
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.listTrails)
        val addButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
//        prefs = this.getPreferences(Context.MODE_PRIVATE)
//        list = prefs.getStringSet("TASK_LIST", null)?.toMutableList() ?: mutableListOf<String>()
        recyclerView.adapter = TrailListAdapter(this, myDataset) //need to add dataset as second parameter
        recyclerView.setHasFixedSize(true)
        addButton.setOnClickListener() {
            intent = Intent(this, TrailEdit::class.java)
            startActivity(intent)
        }
    }
}