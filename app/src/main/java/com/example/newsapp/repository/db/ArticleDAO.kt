package com.example.newsapp.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.Articles

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Articles) : Long

    @Query("SELECT * FROM articles")
    fun getArticles() : LiveData<List<Articles>>

    @Delete
    suspend fun deleteArticle(article: Articles)
}