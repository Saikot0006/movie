package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.BookmarksRowItemBinding
import com.example.movieapp.model.BookmarkModel

class BookMarkAdapter(var callback : (bookMark : BookmarkModel,value : Int) -> Unit) : ListAdapter<BookmarkModel,BookMarkAdapter.BookMarkViewHolder>(BookMarkDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        var binding = BookmarksRowItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return BookMarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        var bookMark = getItem(position)
        holder.bind(bookMark)
        holder.binding.deleteBookMarkRowItemId.setOnClickListener {
            callback(bookMark,1)
        }
        holder.binding.root.setOnClickListener {
            callback(bookMark,2)
        }
    }

    class BookMarkViewHolder(var binding: BookmarksRowItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(bookMark : BookmarkModel){
            binding.bookMark = bookMark
        }
    }

    class BookMarkDiffUtil : DiffUtil.ItemCallback<BookmarkModel>(){
        override fun areItemsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
            return oldItem == newItem
        }
    }


}