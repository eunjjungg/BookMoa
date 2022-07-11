package com.smu.bookmoa


import android.media.Image
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/book.json")
    fun getSearchResult(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") pw: String,
        @Query("query") query: String,
        @Query("display") display: Int

    ): Call<String>
}