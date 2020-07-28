package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.model.TvEpisodes
import com.hacksy.tvmaze.model.TvShows
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiClient {

    private val API_BASE_URL = "https://api.tvmaze.com/"

    private var servicesApiInterface: ServicesApiInterface?=null

    fun build(): ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{
        @GET("shows")
        suspend fun listTvShows(@Query("page")page: Int): Response<List<TvShows>>
        @GET("search/shows")
        suspend fun searchTvShows(@Query("q")show: String): Response<List<TvShowsResponse>>
        @GET("/shows/:id/episodes")
        suspend fun getTvEpisodes(@Path("id")tvShowId: String): Response<List<TvEpisodes>>
    }
}