package com.hacksy.tvmaze.model

import java.io.Serializable


data class TvEpisodes(
    val id: Int,
    val tvShowId: Int,
    val name: String,
    val season: String,
    val number: Int,
    val summary: String,
    val image: Image?
) : Serializable