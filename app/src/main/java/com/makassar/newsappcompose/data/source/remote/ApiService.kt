package com.makassar.newsappcompose.data.source.remote

import com.makassar.newsappcompose.data.response.GetNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String = "b20c0ec3d33f4ed599f61e82a2a7484e"
    ): GetNewsResponse

}