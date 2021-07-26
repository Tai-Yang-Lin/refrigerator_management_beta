package com.example.refrigerator_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBar = supportActionBar
        appBar!!.title="庫冰箱"

        val btn_log = findViewById<Button>(R.id.btn_login)
        btn_log.setOnClickListener {
            val it: Intent= Intent(this, loginpage::class.java)
            startActivity(it)
        }

        val btn_hompage = findViewById<Button>(R.id.btn_homepage)
        btn_hompage.setOnClickListener {
            val it: Intent = Intent(this,homepage::class.java)
            startActivity(it)
        }

    }
}