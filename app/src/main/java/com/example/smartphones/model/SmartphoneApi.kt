package com.example.smartphones.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SmartphoneApi {

    @GET("products/")
    suspend fun fetchAllSmartphones(): Response<List<SmartphoneItem>>

    @GET("details/{id}")
    suspend fun fetchOneSmartphone(@Path("id") id: Int): Response<SmartphoneDetails>

}