package com.example.newsapp.repository.datasource
import ArticleDataSource
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp.model.Articles
import kotlinx.coroutines.CoroutineScope
class ArticleDataSourceFactory(private val  scope: CoroutineScope) : DataSource.Factory<Int, Articles>() {
    val articleDataSourceLiveData = MutableLiveData<ArticleDataSource>()
    override fun create(): DataSource<Int, Articles> {
        val newArticleDataSource =ArticleDataSource(scope)
        articleDataSourceLiveData.postValue(newArticleDataSource)
        return newArticleDataSource
    }
}