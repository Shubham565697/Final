package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var btnSign: Button = findViewById(R.id.btnSignUp)
        var btnLogin: Button = findViewById(R.id.btnLogin)
        btnSign.setOnClickListener() {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }


    }
}