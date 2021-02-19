package com.example.medivex

import LResponse
import MedvixApi
import MyApiRequest
import ServiceBuilder
import com.example.medivex.Models.Users



class UserRepository:MyApiRequest() {




    suspend fun registerUser(user: Users): LResponse{
        return apiRequest{
            myApi.registerUsers(user)
        }
    }

    suspend fun checkUser(username : String,password : String): LResponse{
        return apiRequest {
            myApi.checkUser(username,password)
        }

    }


}