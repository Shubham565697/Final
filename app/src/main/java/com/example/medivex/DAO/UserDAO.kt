package com.example.medivex.DAO

import androidx.room.*
import com.example.medivex.Entity.User.User
@Dao
interface UserDAO {


    @Insert
   suspend fun insert(user:User)

   @Query("select * from User where username= (:username) and password = (:password)")
   suspend fun login(username:String,password:String ):User
   @Query("select * from User")
   suspend fun getData():MutableList<User>
   @Delete
   suspend fun  delete(user:User)

}