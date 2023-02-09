package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.PopularMovieModel
import com.example.movieapp.retrofit.MovieApi
import retrofit2.http.Header

class NowShowingRepository(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao) {

    private val mowShowingLiveData = MutableLiveData<NowShowingMovieModel>()
    private val popularLiveData = MutableLiveData<PopularMovieModel>()

    val movieResult : LiveData<NowShowingMovieModel>
    get() = mowShowingLiveData

    val popularMovie : LiveData<PopularMovieModel>
    get() = popularLiveData


    suspend fun getNowShowingMovie(page : Int) {
        try {
            var result =  movieApi.getNowShowingMovie(page)

            if(result!=null){
                mowShowingLiveData.postValue(result.body())
            }
        }catch (e:Exception){}
    }

    suspend fun getPopularMovie(page : Int){
        try {
            var popularMovieResult = movieApi.getPopularMovie(page)
            if(popularMovieResult!=null){
                popularLiveData.postValue(popularMovieResult.body())
                Log.e("popularMovieResult", "getPopularMovie: "+popularMovieResult.body() )
            }
        }catch (e:Exception){}

    }

    suspend fun getGenre(){
        var genreResult = movieApi.getGenre()
        if(genreResult!=null){
            try {
                movieDao.insertGenre(genreResult.body()!!.genres)
            }catch (e :Exception){}

        }
    }

}