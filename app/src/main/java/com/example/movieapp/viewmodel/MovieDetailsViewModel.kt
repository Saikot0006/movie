package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.db.MovieDB
import com.example.movieapp.model.BookmarkModel
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
        private lateinit var dao: MovieDao
        init {
            dao = MovieDB.getDB(application).getDao()
            movieApi = RetrofitInstance.getRetrofitInstance().create(MovieApi::class.java)
            repository = MovieDetailsRepository(dao,movieApi)
        }

      fun getMovieDetails() : LiveData<MovieDetailsModel>{
         viewModelScope.launch {
             repository.getMovieDetails()
         }
        return repository.detailsLiveData
    }

    fun insertBookMarks(bookmarkModel: BookmarkModel){
        viewModelScope.launch {
            repository.insertBookMarks(bookmarkModel)
        }
    }

     fun deleteBookMarks(id: Long){
        viewModelScope.launch {
            try {
                repository.deleteBookMarks(id)
            }catch (e:Exception){}
        }
    }

     fun getMovieById(bookmarkId : Long) : LiveData<BookmarkModel>{
        viewModelScope.launch {
            try {
                repository.getMovieById(bookmarkId)
            }catch (e:Exception){}
        }
        return repository.favoriteLiveData

    }

    }