package com.example.medivex.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.medivex.Models.Medicine

@Dao
interface MedicineDAO  {


    @Query("select * from Medicine")
    suspend fun getUser():MutableList<Medicine>


}