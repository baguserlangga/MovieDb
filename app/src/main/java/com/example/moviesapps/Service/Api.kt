package com.example.moviesapps.Service

import com.example.moviesapps.Model.ResponseGetLatestMovie
import com.example.moviesapps.Model.ResponseGetListGenre
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface Api {
    @GET("movie/latest?api_key=7916ace8a965a1c3413cd5231af30364&language=en-US")
    fun getListLatestMovies(
        @QueryMap params : Map<String,String>


//        @Query("company_information_id") id: Int
    ): Call<ResponseGetLatestMovie>
    @GET("https://api.themoviedb.org/3/genre/movie/list?")
    fun getListGenre(
        @QueryMap params : Map<String,String>


//        @Query("company_information_id") id: Int
    ): Call<ResponseGetListGenre>
}