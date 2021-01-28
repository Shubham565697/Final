package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.medivex.Entity.User.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var btnSign: Button = findViewById(R.id.btnSignUp)
        var btnLogin: Button = findViewById(R.id.btnLogin)
        val fullname :EditText = findViewById(R.id.Fullname)
        val username :EditText = findViewById(R.id.Username)
        val password :EditText = findViewById(R.id.Password)
        val cpassword :EditText = findViewById(R.id.CPassword)
        val male :RadioButton = findViewById(R.id.male)
        val female :RadioButton = findViewById(R.id.female)
        val other :RadioButton = findViewById(R.id.other)
        btnSign.setOnClickListener() {
            var gender =""
            var img=""


            if(male.isChecked)
            {
                gender ="Male"
                img="https://png.pngtree.com/png-vector/20190411/ourmid/pngtree-business-male-icon-vector-png-image_916468.jpg"
            }
            if(female.isChecked)
            {
                gender ="female"
                img="https://cdn1.iconfinder.com/data/icons/website-internet/48/website_-_female_user-512.png"

            }
            if(other.isChecked)
            {
                gender="others"
                img=""
            }



CoroutineScope(Dispatchers.IO).launch {
    UserDB.getInstance(this@sign_up ).getUserDAO().insert(User(fullname.text.toString(),gender,username.text.toString(),password.text.toString(),img))
withContext(Dispatchers.Main){
    Toast.makeText(this@sign_up, "User Registered", Toast.LENGTH_SHORT).show()

}

}







//            val d = AlertDialog.Builder(this)
//            d.setTitle("Login Confirmation")
//
//            d.setMessage("Do you want to login now?")
//            d.setPositiveButton("Yes"){dialog,which->
//
//                startActivity(Intent(this@sign_up,login::class.java))
//                //a.add(food.id)
//
//
//            }
//            d.setNegativeButton("No"){dialog, which ->
//
//            }
//            val alert = d.create()
//
//            alert.setCancelable(true)
//            alert.show()
//
        }

        btnLogin.setOnClickListener() {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        }


    }
