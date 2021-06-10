package com.cleanarchitecture.network.services


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {

    @GET("/3/movie/{id_movie}?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    fun getDetail(@Path("id_movie") id: String): Call<DetailResponse>

    @GET("3/movie/{movie_id}?")
    fun getMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ""
    ): Call<MovieDetail>

    @GET("3/tv/{series_id}?")
    fun getSeries(
        @Path("series_id") series_id: Int,
        @Query("api_key") api_key: String = "BuildConfig.TMDB_API"
    ): Call<SeriesDetail>

    @GET("3/discover/movie")
    fun getDiscoverMovies(
        @Query("api_key") api_key: String = "BuildConfig.TMDB_API"
    ): Call<DiscoverMovieResponse>

    @GET("3/discover/tv")
    fun getDiscoverSeries(
        @Query("api_key") api_key: String = "BuildConfig.TMDB_API"
    ): Call<DiscoverSeriesResponse>

    @GET("3/search/movie")
    fun searchMovies(
        @Query("query") queryWord: String,
        @Query("api_key") api_key: String = "BuildConfig.TMDB_API"
    ): Call<DiscoverMovieResponse>

    @GET("3/search/tv")
    fun searchSeries(
        @Query("query") queryWord: String,
        @Query("api_key") api_key: String = "BuildConfig.TMDB_API"
    ): Call<DiscoverSeriesResponse>
}
