package com.hacksy.tvmaze.data.remote

import com.hacksy.tvmaze.model.TvEpisodes
import com.hacksy.tvmaze.model.TvShows

data class TvEpisodesResponse(val score:Double, val show: TvEpisodes){
    fun isSuccess():Boolean= (score>0)
}