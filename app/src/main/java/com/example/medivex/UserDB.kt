//package com.example.medivex
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.medivex.DAO.UserDAO
//import com.example.medivex.Entity.User.User
//
//@Database(
//    entities = [User::class],
//    version =1
//)
//abstract class UserDB:RoomDatabase() {
//
//    abstract fun getUserDAO():UserDAO
//
//
//
//    companion object{
//
//        var intance:UserDB?=null
//        fun getInstance(context: Context):UserDB{
//
//            if(intance==null)
//            {
//
//                synchronized(UserDB::class){
//
//                    intance = buildDatabase(context)
//                }
//            }
//
//            return intance!!
//        }
//
//
//        private fun buildDatabase(context:Context):UserDB{
//
//
//            return Room.databaseBuilder(context.applicationContext,UserDB::class.java,"UserDB").build()
//        }
//
//
//
//    }
//
//
//
//
//
//}