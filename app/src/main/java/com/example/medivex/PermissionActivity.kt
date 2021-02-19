package com.example.medivex

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import androidx.core.app.ActivityCompat

class PermissionActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val web:WebView = findViewById(R.id.web)
        web.loadUrl("https://www.clinicone.com.np/clinicone-pharmacy-kathmandu/")
        var reg: ImageButton = this.findViewById(R.id.reg)
        reg.setOnClickListener(){
            startActivity(Intent(this@PermissionActivity,MainActivity::class.java))
        }
//        // Check for permission
//        if (!hasPermission()) {
//            requestPermission()
//            startActivity(Intent(this@PermissionActivity,login::class.java))
//
//        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@PermissionActivity,
            permissions, 1
        )

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
