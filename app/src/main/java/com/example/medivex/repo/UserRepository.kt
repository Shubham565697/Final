package com.example.medivex.repo

import com.example.medivex.ImageResponse
import com.example.medivex.LoginResponse
import com.example.medivex.MedicineResponse
import com.example.medivex.Models.Users
import com.example.medivex.api.MyApiRequest
import com.example.medivex.api.ServiceBuilder
import com.example.medivex.api.UserAPI
import okhttp3.MultipartBody


class UserRepository: MyApiRequest(){


    val myApi = ServiceBuilder.buildServices(UserAPI::class.java)

    suspend fun registerUser(user: Users): LoginResponse {

        return apiRequest {
            myApi.registerUser(user)

        }

    }



    suspend fun Login(username:String,password:String):LoginResponse
    {
        return apiRequest {
            myApi.Login(username,password)
        }


    }

    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            myApi.uploadImage( id, body)
        }
    }

    suspend fun get():MedicineResponse{
        return apiRequest{
            myApi.get()
        }

    }
}