package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            startActivity(Intent(this@Splash,login::class.java))
        }
    }
}