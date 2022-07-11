package com.smu.bookmoa

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.converter.scalars.ScalarsConverterFactory




class ApiClient {
    private val BASE_URL: String = "https://openapi.naver.com/v1/"
    lateinit var retrofit: Retrofit

    public fun getInstance(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}