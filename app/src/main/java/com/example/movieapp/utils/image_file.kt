package com.example.movieapp.utils

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.R


@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url : String){
    if(url!=null){
        var loadUri = "https://image.tmdb.org/t/p/w500/"+url
        Glide.with(this.context).load(loadUri).into(this)
    }else{
        Glide.with(this.context).load(R.drawable.movie_placeholder).into(this)
    }

}

/*@BindingAdapter("app:setGenreView")
fun setGenreView(linear : LinearLayout, text: String){
    val strs = text.split(",").toTypedArray()

    linear.removeAllViews()
    strs.forEach {
        val dynamicTextview = TextView(linear.context)
        dynamicTextview.text = it
        linear.addView(dynamicTextview)
    }
}*/
