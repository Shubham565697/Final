package com.example.medivex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medivex.Models.Medicine
import com.example.medivex.repo.UserRepository
//import com.example.medivex.Entity.User.User
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.xrest.finalassignment.Class.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.Permission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      var lst:MutableList<Medicine> = mutableListOf()
        var btn:ImageButton = findViewById(R.id.reg)
        var home:ImageButton = findViewById(R.id.hom)
        var rv: RecyclerView = findViewById(R.id.rv)
        home.setOnClickListener(){
            startActivity(Intent(this@MainActivity,PermissionActivity::class.java))
        }


        CoroutineScope(Dispatchers.IO).launch {


val ur = UserRepository()
            val response=ur.get()
            lst = UserDB.getInstance(this@MainActivity).getDAO().getUser()
            val list:MutableList<Medicine>? = response.data
if(list!!.size>lst.size)
{

    lst.clear()
    lst=list
}


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