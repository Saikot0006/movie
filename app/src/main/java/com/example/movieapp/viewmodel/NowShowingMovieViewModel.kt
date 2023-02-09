package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.repository.NowShowingRepository
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class NowShowingMovieViewModel(application: Application)
    : AndroidViewModel(application){
        private lateinit var repository: NowShowingRepository
        private lateinit var movieApi: MovieApi


    init {
            movieApi = RetrofitInstance.getRetrofitInstance()
                .create(MovieApi::class.java)
            repository = NowShowingRepository(movieApi)
        }

    val mResult : LiveData<NowShowingMovieModel>
        get() = repository.movieResult

    fun getNowShowingMovie(page : Int) : LiveData<NowShowingMovieModel>{
        viewModelScope.launch {
            repository.getNowShowingMovie(page)
        }
        return  repository.movieResult
    }

}