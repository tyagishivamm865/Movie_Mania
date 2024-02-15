package com.example.moviesapp.Model

data class MoviesData(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)