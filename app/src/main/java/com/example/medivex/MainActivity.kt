package com.example.medivex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val web:WebView = findViewById(R.id.frameLayout)
        web.loadUrl("https://www.clinicone.com.np/clinicone-pharmacy-kathmandu/")
    }
}