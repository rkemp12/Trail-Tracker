package com.rkemp12.trailtracker
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class TrailEdit: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edit_trail_data)
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        button.setOnClickListener() {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener(){
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}