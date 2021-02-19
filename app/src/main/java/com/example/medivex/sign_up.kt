package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
//import com.example.medivex.Entity.User.User
import com.example.medivex.Models.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class sign_up : AppCompatActivity() {
    private lateinit var etFname: EditText
    private lateinit var etLname: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnAddStudent: Button
    private lateinit var btnAddStuden: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        bind()

        btnAddStudent.setOnClickListener(){
            register()
        }
        btnAddStuden.setOnClickListener(){

         startActivity(Intent(this@sign_up,login::class.java))
        }

    }
    fun bind(){
        etFname = findViewById(R.id.etFname)
        etLname = findViewById(R.id.etLname)
        etAddress = findViewById(R.id.etAdress)
        etPhone = findViewById(R.id.etPhone)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnAddStudent = findViewById(R.id.btnAddStudent)
        btnAddStuden = findViewById(R.id.btnAddStuden)
    }

    fun register(){
        val fname = etFname.text.toString()
        val lname = etLname.text.toString()
        val address = etAddress.text.toString()
        val phone = etPhone.text.toString()
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val male : RadioButton = findViewById(R.id.male)
        val female : RadioButton = findViewById(R.id.female)
        val others : RadioButton = findViewById(R.id.others)
        var gender =""
        val type="Customer@mail.com"
        if(male.isChecked)
        {
            gender ="Male"
        }
        if(female.isChecked)
        {
            gender ="Female"
        }
        if(others.isChecked){
            gender ="others"
        }
        val user = Users(fname=fname, lname=lname, gender = gender,address = address,phone = phone, username=username, password =password,email =type )
        if(password!=confirmPassword)
        {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
        }
        else{
            try{
                val userRepo = UserRepository()
                CoroutineScope(Dispatchers.IO).launch {
                    val response = userRepo.registerUser(user)
                    if(response.success==true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@sign_up, "User Registerred", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
            }



        }

    }
    }
