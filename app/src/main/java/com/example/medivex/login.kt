package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medivex.Entity.User.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        setTitle("Login Form")
        var btnSign: Button = findViewById(R.id.btnSignUp)
        var btnLogin:Button=findViewById(R.id.btnLogin)
        var username: EditText = findViewById(R.id.etEmail)
        var password :EditText = findViewById(R.id.etpassword)
        btnLogin.setOnClickListener(){

            var user :User?=null
            CoroutineScope(Dispatchers.IO).launch {

                user = UserDB.getInstance(this@login).getUserDAO().login(username.text.toString(),password.text.toString())

                withContext(Dispatchers.Main)
                {
                    if(user==null)
                    {

                        Toast.makeText(this@login, "Incorrect Creentials", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        startActivity(Intent(this@login,MainActivity::class.java))

                    }

                }

            }





        }
        btnSign.setOnClickListener() {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }



}