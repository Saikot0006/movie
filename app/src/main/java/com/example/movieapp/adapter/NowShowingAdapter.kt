package com.example.movieapp.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.NowShowingRowItemBinding
import com.example.movieapp.model.NowShowingMovieModel
import com.example.movieapp.model.Result
import com.example.movieapp.utils.ConstraintUtils
import okhttp3.internal.Util

class NowShowingAdapter(val callback: (movie: Result) -> Unit) : androidx.recyclerview.widget.ListAdapter<com.example.movieapp.model.Result,
        NowShowingAdapter.NowShowingViewHolder>(NowShowingDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val binding = NowShowingRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowShowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        var result = getItem(position)
        holder.bind(result)
        holder.binding.root.setOnClickListener {
            ConstraintUtils.movieDetails.nowShowingMovieID = result.id
            Log.e("hell01", "onBindViewHolder: hell01" )
            callback(result)
        }

    }

    class NowShowingViewHolder(val binding : NowShowingRowItemBinding)
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



}