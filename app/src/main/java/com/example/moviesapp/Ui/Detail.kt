package com.example.moviesapp.Ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesapp.Db.MoviesDatabase
import com.example.moviesapp.R
import com.example.moviesapp.Repository.MoviesRepository
import com.example.moviesapp.ViewModel.MovieViewModel
import com.example.moviesapp.ViewModel.MovieViewModelProviderFactory
import com.example.moviesapp.databinding.ActivityDetailBinding
import com.example.moviesapp.databinding.ActivityMoviesBinding

class Detail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesRepository = MoviesRepository(MoviesDatabase.getDatabase(this))
        val viewModelProviderFactory = MovieViewModelProviderFactory(moviesRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MovieViewModel::class.java)

        val imdb = intent.getStringExtra("imdb")

        viewModel.getMovieDetails(imdb!!)
        viewModel.MoviesDetail.observe(this@Detail, Observer {
            val data = it.data
            if(data == null){
                binding.title2.text = "data.Title"
                binding.actors.text = "data.Actors"
                binding.released.text = "data.Released"
                binding.imdbrating.text = "data.imdbID"
            }else {
                binding.title2.text = data!!.Title
                binding.actors.text = data.Actors
                binding.released.text = data.Released
                binding.imdbrating.text = data.imdbID
                Glide.with(this).load(data.Poster).into(binding.imageViewdetails)
            }
    })

    }

}