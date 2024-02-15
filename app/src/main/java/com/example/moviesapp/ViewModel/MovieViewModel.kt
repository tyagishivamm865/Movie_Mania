package com.example.moviesapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.Model.DetailsData
import com.example.moviesapp.Model.MoviesData
import com.example.moviesapp.Model.Search
import com.example.moviesapp.Repository.MoviesRepository
import com.example.moviesapp.Util.Constants.Companion.API_KEY
import com.example.moviesapp.Util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(val movieRepo: MoviesRepository) : ViewModel() {

    val movies: MutableLiveData<Resource<MoviesData>> = MutableLiveData()
    val Searchedmovies: MutableLiveData<Resource<MoviesData>> = MutableLiveData()
    val MoviesDetail: MutableLiveData<Resource<DetailsData>> = MutableLiveData()

 init {
     getMoviesData()
 }

    fun getMoviesData() = viewModelScope.launch {
        movies.postValue(Resource.Loading())
        val response = movieRepo.getMovies(API_KEY,"Happy",1)
        movies.postValue(handleMovieResponse(response))
    }

    private fun handleMovieResponse(response : Response<MoviesData>) : Resource<MoviesData>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun getSearchedMoviesData(searchterm:String,page:Int) = viewModelScope.launch {
        Searchedmovies.postValue(Resource.Loading())
        val response = movieRepo.getSearchedMovies(API_KEY,searchterm,page)
        Searchedmovies.postValue(handleSearchedMovieResponse(response))
    }

    private fun handleSearchedMovieResponse(response : Response<MoviesData>) : Resource<MoviesData>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun getMovieDetails(imdb:String) = viewModelScope.launch {
        MoviesDetail.postValue(Resource.Loading())
        val response = movieRepo.getMovieDetail(imdb, API_KEY)
        MoviesDetail.postValue(handleDetailMovieResponse(response))
    }

    private fun handleDetailMovieResponse(response : Response<DetailsData>) : Resource<DetailsData>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}