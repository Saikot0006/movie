package com.example.movieapp.utils

import android.graphics.Color
import android.view.Gravity
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

@BindingAdapter("app:setGenreView")
fun setGenreView(linear : LinearLayout, text: String){
    val strs = text.split(",").toTypedArray()

    linear.removeAllViews()

    for(i in 0..strs.size - 2){
        val dynamicTextview = TextView(linear.context)
        dynamicTextview.text = strs.get(i)
        dynamicTextview.setBackgroundResource(R.drawable.genres_border)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 10, 15, 5)

        dynamicTextview.layoutParams = params


        linear.addView(dynamicTextview)
    }
    /*strs.forEach {
        val dynamicTextview = TextView(linear.context)
        dynamicTextview.text = it
        dynamicTextview.setBackgroundResource(R.drawable.genres_border)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 10, 15, 5)

        dynamicTextview.layoutParams = params


        linear.addView(dynamicTextview)
    }*/
}
