package com.example.moviesapp.Network

import com.example.moviesapp.Model.DetailsData
import com.example.moviesapp.Model.MoviesData
import com.example.moviesapp.Util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/")
    suspend fun getMovies(
        @Query("apikey")
        apikey: String,
        @Query("s")
        s: String,
        @Query("page")
        page: Int,

    ): Response<MoviesData>

    @GET("/")
    suspend fun getSearchedMovies(
        @Query("apikey")
        apikey: String,
        @Query("s")
        s: String,
        @Query("page")
        page: Int,
        ): Response<MoviesData>

    @GET("/")
    suspend fun getMoviesDetails(
        @Query("i")
        imdbid: String,
        @Query("apikey")
        apikey: String
    ): Response<DetailsData>



}