package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Form")
        var btnSign: Button = findViewById(R.id.btnSignUp)
        var btnLogin:Button=findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnSign.setOnClickListener() {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }



}