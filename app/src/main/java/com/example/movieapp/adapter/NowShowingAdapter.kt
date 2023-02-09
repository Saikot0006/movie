package com.example.movieapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.NowShowingRowItemBinding
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.Result

class NowShowingAdapter : androidx.recyclerview.widget.ListAdapter<com.example.movieapp.model.Result,
        NowShowingAdapter.NowShowingViewHolder>(NowShowingDiffUtil()) {

    class NowShowingViewHolder(private val binding : NowShowingRowItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(nowShowing : com.example.movieapp.model.Result){
                binding.nowShowing = nowShowing
            }
        }

    class NowShowingDiffUtil : DiffUtil.ItemCallback<com.example.movieapp.model.Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val binding = NowShowingRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowShowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        //var  movieList = List<Result>
        var result = getItem(position)
        holder.bind(result)
    }
}