package com.example.refrigerator_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class loginpage : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val appBar = supportActionBar
        appBar!!.title="登入"

        val textlinkreg = findViewById<TextView>(R.id.textView_registered)
        textlinkreg.setOnClickListener {
            val it: Intent=Intent(this,register_page::class.java)
            startActivity(it)
        }

        val  loginbtn = findViewById<Button>(R.id.btn_log)
        loginbtn.setOnClickListener {
            view -> login()
        }
    }

    private fun login(){
        val accountTxt = findViewById<EditText>(R.id.editText_account)
        val passwordTxt = findViewById<EditText>(R.id.editText_password)

        val account = accountTxt.text.toString()
        val password = passwordTxt.text.toString()

        if (!account.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(account, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if(task.isSuccessful){
                    startActivity(Intent(this,homepage::class.java))
                    Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "帳號密碼錯誤",Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this, "請輸入帳號密碼",Toast.LENGTH_LONG).show()
        }
    }
}