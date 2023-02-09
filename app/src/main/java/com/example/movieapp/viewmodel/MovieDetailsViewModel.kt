package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MovieDetailsModel
import com.example.movieapp.repository.MovieDetailsRepository
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.retrofit.RetrofitInstance
import com.example.movieapp.utils.ConstraintUtils
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application)
    : AndroidViewModel(application) {
        private lateinit var repository: MovieDetailsRepository
        private lateinit var movieApi: MovieApi
        init {
            movieApi = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)
            repository = MovieDetailsRepository(movieApi)
        }

      fun getMovieDetails() : LiveData<MovieDetailsModel>{
         viewModelScope.launch {
             repository.getMovieDetails()
         }
        return repository.detailsLiveData
    }


    }