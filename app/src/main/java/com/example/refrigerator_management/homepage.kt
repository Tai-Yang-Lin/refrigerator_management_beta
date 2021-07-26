package com.example.refrigerator_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val appBar = supportActionBar
        appBar!!.title="首頁"

        val btn_fridge = findViewById<Button>(R.id.btn_fridge)
        btn_fridge.setOnClickListener {
            val it: Intent = Intent(this,fridge::class.java)
            startActivity(it)
        }
        val btn_freeze = findViewById<Button>(R.id.btn_freeze)
        btn_freeze.setOnClickListener {
            Toast.makeText(this,"尚未開發敬請期待",Toast.LENGTH_LONG).show()
        }
    }
}