package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.PopularMovieRowItemBinding
import com.example.movieapp.model.PResult
import com.example.movieapp.model.PopularMovieModel

class PopularMovieAdapter :
    ListAdapter<PResult, PopularMovieAdapter.PopularMovieViewHolder>(PopularMovieDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = PopularMovieRowItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        var result = getItem(position)
        holder.bind(result)

    }

    class PopularMovieViewHolder(private var binding : PopularMovieRowItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(popularMovie : PResult){
                binding.popular = popularMovie
            }
        }

    class PopularMovieDiffUtil : DiffUtil.ItemCallback<PResult>(){
        override fun areItemsTheSame(oldItem: PResult, newItem: PResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PResult, newItem: PResult): Boolean {
            return oldItem == newItem
        }

    }


}