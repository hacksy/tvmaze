package com.hacksy.tvmaze.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hacksy.tvmaze.R
import com.hacksy.tvmaze.model.TvShows
import kotlinx.android.synthetic.main.row_tv_show.view.*

class TvShowsAdapter(private var tvShows:List<TvShows>): RecyclerView.Adapter<TvShowsAdapter.TvViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_tv_show, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(vh: TvViewHolder, position: Int) {
        //render
        vh.bind(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    fun update(data:List<TvShows>){
        tvShows= data
        notifyDataSetChanged()
    }

    class TvViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textViewName: TextView = view.textViewName
        private val imageView: ImageView = view.imageView

        fun bind(tvShow:TvShows){
            textViewName.text = tvShow.name
            Glide.with(imageView.context).load(tvShow.image?.medium).into(imageView)
        }
    }
}