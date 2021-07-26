package com.example.refrigerator_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class fridge : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var referance: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fridge)

        database= FirebaseDatabase.getInstance();
        referance=database.getReference("Usres")
        getData()

        val appBar = supportActionBar
        appBar!!.title="冷藏"

        val btn_addfood = findViewById<FloatingActionButton>(R.id.FAB_add)
        btn_addfood.setOnClickListener {
            val it: Intent = Intent(this,add_food_fridge::class.java)
            startActivity(it)
        }
    }

    private fun getData(){
        referance.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                var list=ArrayList<DatabaseModel>()
                for (data in p0.children)
                {
                    var model=data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)
                }
                if (list.size>0){
                    var adapter = DataAdapter(list)
                    val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
                    recyclerview.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("cancel",p0.toString())
            }

        })
    }

}