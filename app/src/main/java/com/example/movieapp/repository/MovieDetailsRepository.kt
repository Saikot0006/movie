package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieDetailsModel
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.utils.ConstraintUtils

class MovieDetailsRepository(private var movieApi: MovieApi) {

    private var detailsMutableData = MutableLiveData<MovieDetailsModel>()

    val detailsLiveData : LiveData<MovieDetailsModel>
    get() = detailsMutableData

    suspend fun getMovieDetails(){
        try {
            val id = ConstraintUtils.movieDetails.nowShowingMovieID
            var result = movieApi.getMovieDetails(id)
            if(result!=null){
                detailsMutableData.postValue(result.body())
                Log.e("details", "getMovieDetails: "+result.body().toString() )
            }
        }catch (e:Exception){}
    }
}