package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.databinding.MovieDetailsGenrsRowItemBinding
import com.example.movieapp.model.Genre

class GenresAdapter : androidx.recyclerview.widget.ListAdapter<Genre,GenresAdapter.GenresViewHolder>(GenreDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        var binding = MovieDetailsGenrsRowItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return GenresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        var result = getItem(position)
        holder.bind(result)
    }
    class GenresViewHolder(val binding : MovieDetailsGenrsRowItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(genre : Genre){
                binding.genre = genre
            }
    }

    class GenreDiffUtil : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }


}