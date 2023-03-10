package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.BookmarkModel
import com.example.movieapp.model.MovieDetailsModel
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.utils.ConstraintUtils

class MovieDetailsRepository(
    private var dao: MovieDao,
    private var movieApi: MovieApi) {

    private var detailsMutableData = MutableLiveData<MovieDetailsModel>()
    private var favoriteMutableData = MutableLiveData<BookmarkModel>()

    val detailsLiveData : LiveData<MovieDetailsModel>
    get() = detailsMutableData

    val favoriteLiveData : LiveData<BookmarkModel>
    get() = favoriteMutableData

    suspend fun getMovieDetails(){
        try {
            val id = ConstraintUtils.movieDetails.nowShowingMovieID
            var result = movieApi.getMovieDetails(id)
            if(result!=null){
                detailsMutableData.postValue(result.body())
            }
        }catch (e:Exception){}
    }

    suspend fun insertBookMarks(bookmarkModel: BookmarkModel){
        try {
            dao.insertBookMarks(bookmarkModel)
        }catch (e:Exception){}

    }

    suspend fun deleteBookMarks(id: Long){
        try {
            dao.deleteBookMarks(id)
        }catch (e:Exception){}
    }

      suspend fun getMovieById(bookmarkId : Long){
        try {
            var result = dao.getMovieById(bookmarkId)
            favoriteMutableData.postValue(result)
            Log.e("favorite", "checkFavorite: favorite00009"+result.toString() )

        }catch (e:Exception){}

    }
}