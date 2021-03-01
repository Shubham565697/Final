package com.example.medivex.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicine (
@PrimaryKey
val _id:String="",
    val Name:String?=null,
val Price:String?=null,
val Qty:String?=null,
val Description:String?=null,
val Image:String?=null,
    val Rating:Float?=null



)