package com.example.moviesapp.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.Db.MoviesDatabase
import com.example.moviesapp.R
import com.example.moviesapp.Repository.MoviesRepository
import com.example.moviesapp.ViewModel.MovieViewModel
import com.example.moviesapp.ViewModel.MovieViewModelProviderFactory
import com.example.moviesapp.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesRepository = MoviesRepository(MoviesDatabase.getDatabase(this))
        val viewModelProviderFactory = MovieViewModelProviderFactory(moviesRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MovieViewModel::class.java)

        moveToAnotherFragment(MoviesFragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                      moveToAnotherFragment(MoviesFragment())
                    true
                }
                R.id.navigation_savedMovies -> {
                       moveToAnotherFragment(SavedFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager

        if (fragmentManager.getBackStackEntryCount() > 1) {
            super.onBackPressed()
        } else {

        }
    }

    fun moveToAnotherFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}