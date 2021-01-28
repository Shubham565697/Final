package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medivex.Entity.User.User
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lst:MutableList<User> = mutableListOf()
        var btn:Button = findViewById(R.id.reg)

        var rv: RecyclerView = findViewById(R.id.rv)


        CoroutineScope(Dispatchers.IO).launch {

            lst = UserDB.getInstance(this@MainActivity).getUserDAO().getData()

            withContext(Dispatchers.Main){
                var adapter = UserAdapter(lst,this@MainActivity)
                rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
                rv.adapter = adapter

            }

        }





btn.setOnClickListener(){

    startActivity(Intent(this@MainActivity,sign_up::class.java))
}
    }






}