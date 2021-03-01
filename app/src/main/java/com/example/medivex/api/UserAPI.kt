package com.example.medivex.api

import com.example.medivex.ImageResponse
import com.example.medivex.LoginResponse
import com.example.medivex.MedicineResponse
import com.example.medivex.Models.Users
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @POST("insert")
    suspend fun registerUser(@Body user: Users): Response<LoginResponse>

    @FormUrlEncoded
    @POST("/login")
    suspend fun Login(
        @Field("username") username:String,
        @Field("password") password:String): Response<LoginResponse>


    @Multipart
    @PUT("photo/{id}")
    suspend fun uploadImage(

        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>


    @GET("/product/all")
    suspend fun get():Response<MedicineResponse>
}