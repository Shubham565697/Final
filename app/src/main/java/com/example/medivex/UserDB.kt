package com.example.medivex

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medivex.DAO.MedicineDAO
import com.example.medivex.Entity.User.User
import com.example.medivex.Models.Medicine

@Database(
    entities  =[Medicine::class],
    version =1,
    exportSchema = false

)
abstract class UserDB:RoomDatabase() {


    abstract fun getDAO():MedicineDAO
    companion object{
        var instance:UserDB?=null



        fun getInstance(context:Context):UserDB{

            if(instance==null)
            {

                synchronized(UserDB::class){
                    instance= builDatabase(context)

                }
            }
            return instance!!

        }


       fun builDatabase(context:Context):UserDB{
           return Room.databaseBuilder(context,UserDB::class.java,"MedVix").build()
       }


    }



}