package com.example.medivex

import LResponse
import MedvixApi
import MyApiRequest
import ServiceBuilder
import com.example.medivex.Models.Users



class UserRepository:MyApiRequest() {


    val myApi =  ServiceBuilder.buildServices(MedvixApi::class.java)

    suspend fun registerUser(user: Users): LResponse{
        return apiRequest{
            myApi.registerUsers(user)
        }
    }




}