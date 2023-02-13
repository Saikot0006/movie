package com.example.movieapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.Genre
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.PopularMovieModel
import com.example.movieapp.retrofit.MovieApi
import com.example.movieapp.utils.InternetConnectionCheck
import retrofit2.http.Header

class NowShowingRepository(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
    private val context: Context) {

    private val mowShowingLiveData = MutableLiveData<NowShowingMovieModel>()
    private val popularLiveData = MutableLiveData<PopularMovieModel>()
    private val genreMutableLiveData = MutableLiveData<List<Genre>>()

    val movieResult : LiveData<NowShowingMovieModel>
    get() = mowShowingLiveData

    val popularMovie : LiveData<PopularMovieModel>
    get() = popularLiveData

    val genreLiveData : LiveData<List<Genre>>
    get() = genreMutableLiveData


    suspend fun getNowShowingMovie(page : Int) {
        try {

            if(InternetConnectionCheck.isNetworkAvailable(context)){
                var result =  movieApi.getNowShowingMovie(page)

                if(result!=null){
                    mowShowingLiveData.postValue(result.body())
                    Log.e("getNowShowingMovie", "getNowShowingMovie: "+result.body())
                }
            }

        }catch (e:Exception){}
    }

    suspend fun getPopularMovie(page : Int){
        try {
            if(InternetConnectionCheck.isNetworkAvailable(context)){
                var popularMovieResult = movieApi.getPopularMovie(page)
                if(popularMovieResult!=null){
                    popularLiveData.postValue(popularMovieResult.body())
                    Log.e("popularMovieResult", "getPopularMovie: "+popularMovieResult.body() )
                }
            }

        }catch (e:Exception){}

    }

    suspend fun getGenre(){
        if(InternetConnectionCheck.isNetworkAvailable(context)){
            var genreResult = movieApi.getGenre()
            if(genreResult!=null){
                try {
                    movieDao.insertGenre(genreResult.body()!!.genres)
                }catch (e :Exception){}

            }
        }

    }


    suspend fun getGenreDataByID(id : Int) : MutableLiveData<List<Genre>>{
        try {
            genreMutableLiveData.postValue(movieDao.getGenreDataByID(id))
        }catch (e:Exception){}

        return genreMutableLiveData
    }

    /*suspend fun getAllGenre() : MutableLiveData<List<Genre>>{
        try {
            genreMutableLiveData.postValue(movieDao.getAllGenre())
        }catch (e:Exception){}

        return genreMutableLiveData
    }*/


}