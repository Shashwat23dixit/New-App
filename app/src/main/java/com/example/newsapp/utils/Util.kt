package com.example.newsapp.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.model.Articles

fun shareNews(context: Context?, articles: Articles){
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, articles.urlToImage)
        putExtra(Intent.EXTRA_TITLE, articles.title)
        type = "image/*"
    }
    context?.startActivity(Intent.createChooser(intent, "Share News On"))
}

// Load image in image view

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun getCircularDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 48f
        setTint(context.resources.getColor(R.color.bgLineColor))
        start()
    }
}

fun ImageView.loadImage(url : String, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
//        .error(R.drawable.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@BindingAdapter("loadImage")
fun loadImage(imageView : ImageView, url : String?){
    if (url != null){
        imageView.loadImage(url!!, getCircularDrawable(imageView.context))
    }


}