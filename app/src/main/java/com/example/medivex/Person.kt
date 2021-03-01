package com.xrest.finalassignment.Class


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File


@Entity
data class Person(
        val _id :String?=null,
        var FirstName:String?=null,
        var LastName:String?=null,
        var Gender:String?=null,
        var Address:String?=null,
        var PhoneNumber :String?=null,
        var Username :String?=null,
        var Password :String?=null,
        var Profile:String?=null,
        var UserType:String?=null



) {
    @PrimaryKey(autoGenerate = true)
    var userID =0


}