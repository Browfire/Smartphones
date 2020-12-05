package com.example.smartphones.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SmartphoneRetrofitClient {

    companion object{
        private const val BASE_URL= "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun retrofitInstance(): SmartphoneApi {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(SmartphoneApi::class.java)
        }

    }
    
}