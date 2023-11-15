package com.makassar.newsappcompose.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.makassar.newsappcompose.data.repository.NewsRepository
import com.makassar.newsappcompose.data.source.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val newsRepository: NewsRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://newsapi.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val newsRepository: NewsRepository
        get() = NewsRepository(retrofitService)

}