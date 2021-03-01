package com.example.medivex

import com.example.medivex.Models.Medicine

data class MedicineResponse(
    val success:Boolean?=null,
    val data:MutableList<Medicine>?=null
)