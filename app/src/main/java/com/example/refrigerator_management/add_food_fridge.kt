package com.example.refrigerator_management

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val REQUEST_CODE = 42
class add_food_fridge : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    private lateinit var referance:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_fridge)

        database= FirebaseDatabase.getInstance();
        referance=database.getReference("Usres")

        val appBar = supportActionBar
        appBar!!.title="新增冷藏食物"

        val btn_addfood = findViewById<Button>(R.id.btn_addfood)
        btn_addfood.setOnClickListener {
            sendData()
            val it: Intent = Intent(this,fridge::class.java)
            startActivity(it)
        }

        val btnTakePicture = findViewById<Button>(R.id.btn_camera)
        btnTakePicture.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(takePictureIntent.resolveActivity(this.packageManager) != null){
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            }else{
                Toast.makeText(this,"相機權限沒開", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
            val imageViewfood = findViewById<ImageView>(R.id.imageView_food)
            imageViewfood.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun sendData(){
        var foodname = findViewById<EditText>(R.id.editText_foodname)
        var dateline = findViewById<EditText>(R.id.editTextDateline)

        var name = foodname.text.toString().trim()
        var date = dateline.text.toString().trim()
        if (name.isNotEmpty() && date.isNotEmpty()){
            var id =referance.push().key
            var model = DatabaseModel(name,date,id!!)
            referance.child(id!!).setValue(model)
            Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"請輸入食物名及到期日",Toast.LENGTH_LONG).show()
        }
    }
}