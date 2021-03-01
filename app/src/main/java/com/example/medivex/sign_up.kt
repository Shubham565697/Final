package com.example.medivex

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
//import com.example.medivex.Entity.User.User
import com.example.medivex.Models.Users
import com.example.medivex.repo.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

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
    private lateinit var img: ImageView

    private val gallery_code=0
    private val camera_code=1
    var image:String?=null
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
        img.setOnClickListener(){

            val popupMenu = PopupMenu(this@sign_up, img)
            popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menuCamera ->
                        openCamera()
                    R.id.menuGallery ->
                        openGallery()
                }
                true
            }
            popupMenu.show()
        }

    }
    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,gallery_code)

    }

    fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,camera_code)


    }

    fun bind(){
        img = findViewById(R.id.img)
        etFname =
            findViewById(R.id.etFname)
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
        val user = Users(fname=fname, lname=lname, gender = gender,address = address,phone = phone, username=username, password =password )
        if(password!=confirmPassword)
        {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
        }
        else{
            try{
                val userRepo = UserRepository()
                CoroutineScope(Dispatchers.IO).launch {
                    val response = userRepo.registerUser(user)
                    if(response.status==true) {
                        withContext(Main){
                            Toast.makeText(this@sign_up, "lllllll", Toast.LENGTH_SHORT).show()
                        }
                        if (image != null){


                           uploadImage(response.data!!.toString())
                        }

                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@sign_up, "User Registerred", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
            }
            catch(ex:Exception)
            {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@sign_up, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                }

            }


        }

    }




    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    private fun uploadImage(studentId: String) {
        if (image != null) {
            val file = File(image!!)
            val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("file", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val studentRepository = UserRepository()
                    val response = studentRepository.uploadImage(studentId, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@sign_up, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                            this@sign_up,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == gallery_code && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                image = cursor.getString(columnIndex)
                img.setImageBitmap(BitmapFactory.decodeFile(image))
                cursor.close()
            } else if (requestCode == camera_code && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                image = file!!.absolutePath
                img.setImageBitmap(BitmapFactory.decodeFile(image))
            }
        }
    }


    }
