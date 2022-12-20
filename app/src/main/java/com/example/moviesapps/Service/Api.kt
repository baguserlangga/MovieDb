package com.example.moviesapps.Service

import com.example.moviesapps.Model.MovieModel
import com.example.moviesapps.Model.ResponseGetLatestMovie
import com.example.moviesapps.Model.ResponseGetListGenre
import com.example.moviesapps.Model.ResponseMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface Api {
    @GET("movie/latest?api_key=7916ace8a965a1c3413cd5231af30364&language=en-US")
    fun getListLatestMovies(
        @QueryMap params : Map<String,String>
    ): Call<ResponseGetLatestMovie>
    @GET("https://api.themoviedb.org/3/genre/movie/list?")
    fun getListGenre(
        @QueryMap params : Map<String,String>
    ): Call<ResponseGetListGenre>
    @GET("https://api.themoviedb.org/3/movie/top_rated?")
    fun getListTopratedMovie(
        @QueryMap params : Map<String,String>
    ): Call<ResponseMovie>
}