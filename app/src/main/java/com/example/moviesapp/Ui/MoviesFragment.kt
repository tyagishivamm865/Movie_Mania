package com.example.moviesapp.Ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.Db.MoviesDatabase
import com.example.moviesapp.Network.RetrofitInstance
import com.example.moviesapp.R
import com.example.moviesapp.Util.Resource
import com.example.moviesapp.ViewModel.MovieViewModel
import com.example.moviesapp.adapters.MoviesAdapter
import com.example.moviesapp.databinding.FragmentMoviesBinding
import java.io.LineNumberReader

class MoviesFragment : Fragment() {

lateinit var viewModel:MovieViewModel
lateinit var moviesAdapter: MoviesAdapter
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    companion object{
        const val REQUESTCODE = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MoviesActivity).viewModel


        moviesAdapter = MoviesAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = moviesAdapter
        }


        val SearchString = binding.searchEditText.text
        binding.searchButton.setOnClickListener {
            if(SearchString.isNullOrEmpty()){
                binding.searchEditText.error = "Field is must for Search"
            }
            viewModel.getSearchedMoviesData(SearchString.toString(),1)

            viewModel.Searchedmovies.observe(viewLifecycleOwner, Observer {response ->
                when(response){
                    is Resource.Success ->{
                        hideProgressBar()
                        response.data?.let {
                            moviesAdapter.differ.submitList(it.Search)
                        }
                    }
                    is Resource.Error ->{
                        hideProgressBar()
                        response.message?.let {
                            Log.d("MoviesFragmentError",it)
                        }
                    }

                    is Resource.Loading ->{
                        showProgressBar()
                    }
                }
            })
        }


        viewModel.movies.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {
                        moviesAdapter.differ.submitList(it.Search)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {
                        Log.d("MoviesFragmentError",it)
                    }
                }

                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}