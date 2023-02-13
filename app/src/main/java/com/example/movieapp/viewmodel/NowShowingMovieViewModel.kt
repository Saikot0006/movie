package com.example.movieapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.db.MovieDB
import com.example.movieapp.model.Genre
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.PopularMovieModel
import com.example.movieapp.repository.NowShowingRepository
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class NowShowingMovieViewModel(application: Application)
    : AndroidViewModel(application){
        private lateinit var repository: NowShowingRepository
        private lateinit var movieApi: MovieApi
        private lateinit var dao : MovieDao


    init {
            movieApi = RetrofitInstance.getRetrofitInstance()
                .create(MovieApi::class.java)
            dao = MovieDB.getDB(application).getDao()
            repository = NowShowingRepository(movieApi,dao,application)
        }

    val NowShowingMovieResult : LiveData<NowShowingMovieModel>
        get() = repository.movieResult

    val popularMovieResult : LiveData<PopularMovieModel>
        get() = repository.popularMovie


    fun getNowShowingMovie(page : Int) : LiveData<NowShowingMovieModel>{
        viewModelScope.launch {
            repository.getNowShowingMovie(page)
        }
        return  repository.movieResult
    }

    fun getPopularMovie(page : Int) : LiveData<PopularMovieModel>{
        viewModelScope.launch {
            repository.getPopularMovie(page)
        }
        return repository.popularMovie
    }

     fun getGenre() {
        viewModelScope.launch {
            repository.getGenre()
        }
    }

     fun getGenreDataByID(id : Int) : LiveData<List<Genre>>{
        try {
            viewModelScope.launch {
                repository.getGenreDataByID(id)
            }
        }catch (e:Exception){}
         return repository.genreLiveData
    }


    /*fun getAllGenre() : LiveData<List<Genre>>{
        try {
            viewModelScope.launch {
                repository.getAllGenre()
            }
        }catch (e:Exception){}
         return repository.genreLiveData
    }*/


}