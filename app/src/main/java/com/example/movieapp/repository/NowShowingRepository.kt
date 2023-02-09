package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.retrofit.MovieApi

class NowShowingRepository(private val movieApi: MovieApi) {

    private val mowShowingLiveData = MutableLiveData<NowShowingMovieModel>()

    val movieResult : LiveData<NowShowingMovieModel>
    get() = mowShowingLiveData

    suspend fun getNowShowingMovie(page : Int) {
        var result =  movieApi.getNowShowingMovie(page)

        if(result!=null){
            mowShowingLiveData.postValue(result.body())
        }
    }

}