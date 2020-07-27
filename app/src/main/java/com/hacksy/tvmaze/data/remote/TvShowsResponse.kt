package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.model.TvShows


data class TvShowsResponse(val status:Int?, val msg:String?, val data:List<TvShows>?){
    fun isSuccess():Boolean= (status==200)
}