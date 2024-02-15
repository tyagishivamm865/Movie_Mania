package com.example.moviesapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.Repository.MoviesRepository

class MovieViewModelProviderFactory(val moviesRepository: MoviesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(moviesRepository) as T
    }
}