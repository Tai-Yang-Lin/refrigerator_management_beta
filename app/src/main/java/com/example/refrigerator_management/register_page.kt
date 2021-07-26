package com.example.refrigerator_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class register_page : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        val register_btn = findViewById<Button>(R.id.btn_reg)
        register_btn.setOnClickListener {
            view -> register()
        }

        val appBar = supportActionBar
        appBar!!.title="註冊"
    }

    private fun register(){
        val accountTxt = findViewById<EditText>(R.id.editTextreg_account)
        val passwordTxt = findViewById<EditText>(R.id.editTextreg_password)

        val account = accountTxt.text.toString()
        val password = passwordTxt.text.toString()

        if (!account.isEmpty() && !password.isEmpty()){
            mAuth.createUserWithEmailAndPassword(account,password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"註冊成功，請重新登入", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,loginpage::class.java))
                }else{
                    Toast.makeText(this,"錯誤！請注意是否有遵照帳號密碼規則 :(", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this,"請輸入帳號密碼 :(", Toast.LENGTH_LONG).show()
        }
    }
}