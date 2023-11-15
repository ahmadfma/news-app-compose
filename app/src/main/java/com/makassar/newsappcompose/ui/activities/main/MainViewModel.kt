package com.makassar.newsappcompose.ui.activities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.makassar.newsappcompose.MyApplication
import com.makassar.newsappcompose.data.models.Article
import com.makassar.newsappcompose.data.repository.NewsRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val news: List<Article>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val newsRepository: NewsRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getNews() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = newsRepository.getNews(
                q = "pemilu",
                from = "2023-10-15",
                sortBy = "publishedAt"
            )
            Log.d("MainViewModel", "getNews: ${result.articles?.size}")
            mainUiState = MainUiState.Success(result.articles.orEmpty())
        } catch (e: IOException) {
            Log.d("MainViewMode", "getNews error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getNews()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val newsRepository = application.container.newsRepository
                MainViewModel(newsRepository)
            }
        }
    }

}