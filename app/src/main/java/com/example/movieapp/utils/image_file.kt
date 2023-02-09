package com.example.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url : String){
    var loadUri = "https://image.tmdb.org/t/p/w500/"+url
    Glide.with(this.context).load(loadUri).into(this)
}