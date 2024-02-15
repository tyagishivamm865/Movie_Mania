package com.example.moviesapp.Repository

import com.example.moviesapp.Db.MoviesDatabase
import com.example.moviesapp.Network.RetrofitInstance

class MoviesRepository(val db:MoviesDatabase) {

    suspend fun getMovies(api:String,s:String,page:Int) = RetrofitInstance().api.getMovies(api,s,page)
    suspend fun getSearchedMovies(api:String,s:String,page:Int) = RetrofitInstance().api.getSearchedMovies(api,s,page)
    suspend fun getMovieDetail(imdb:String,api: String) = RetrofitInstance().api.getMoviesDetails(imdb,api)
}