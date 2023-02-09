package com.example.movieapp.retrofit

import com.example.movieapp.model.NowShowingMovieModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import javax.security.auth.callback.Callback

interface MovieApi {

    @GET("3/movie/now_playing?api_key=19c1c2d504f8ac3c45453893ebb0e54d&language=en-US")
    suspend fun getNowShowingMovie(@Header("page") page : Int) : Response<NowShowingMovieModel>
}