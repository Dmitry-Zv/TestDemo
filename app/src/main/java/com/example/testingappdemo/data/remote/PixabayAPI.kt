package com.example.testingappdemo.data.remote

import com.example.testingappdemo.BuildConfig
import com.example.testingappdemo.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}