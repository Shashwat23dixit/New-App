package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.Source
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "articles"
)

data class Articles(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,

) : Serializable
