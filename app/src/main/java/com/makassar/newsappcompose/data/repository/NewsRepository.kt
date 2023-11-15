package com.makassar.newsappcompose.data.repository

import com.makassar.newsappcompose.data.response.GetNewsResponse
import com.makassar.newsappcompose.data.source.remote.ApiService

class NewsRepository(private val apiService: ApiService) {

    suspend fun getNews(q: String, from: String, sortBy: String): GetNewsResponse {
        return apiService.getNews(q, from, sortBy)
    }

}