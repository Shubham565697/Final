package com.example.medivex

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
//import com.example.medivex.Entity.User.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class login : AppCompatActivity() {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if (!hasPermission()) {
            requestPermission()
        }


        setTitle("Login Form")
        var btnSign: Button = findViewById(R.id.btnSignUp)
        var btnLogin:Button=findViewById(R.id.btnLogin)
        var username: EditText = findViewById(R.id.etEmail)
        var password :EditText = findViewById(R.id.etpassword)
        btnLogin.setOnClickListener(){
            val us = UserRepository()
            CoroutineScope(Dispatchers.IO).launch {
                val response = us.checkUser(username.text.toString(), password.text.toString())
                if (response.success == true) {
                    var intent = Intent(this@login, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    withContext(Main) {

                        Toast.makeText(this@login, "Invalid Credentials", Toast.LENGTH_SHORT).show()

                    }
                }
            }





        }
        btnSign.setOnClickListener() {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }


    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }


}