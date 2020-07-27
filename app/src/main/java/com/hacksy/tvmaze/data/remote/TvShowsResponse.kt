package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.model.TvShows


data class TvShowsResponse(val score:Double, val show:TvShows){
    fun isSuccess():Boolean= (score>0)
}