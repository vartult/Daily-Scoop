package com.cellfishpool.news.database

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cellfishpool.news.R
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.network.model.ResponseNews
import com.cellfishpool.news.network.model.toRoomResult
import com.cellfishpool.news.network.service.ApiService
import com.cellfishpool.news.utils.Constants
import com.cellfishpool.news.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: ArticleDatabase,
    private val networkUtil: NetworkUtil,
    private val sharedPreferences: SharedPreferences
) {

    val articleLiveData = MutableLiveData<List<ArticleRoom>>()
    val searchLiveData= MutableLiveData<List<ArticleRoom>>()
    private fun isConnected(): Boolean {
        return networkUtil.isNetworkAvailable()
    }

    suspend fun fetchArticleDataCoroutine() {
        withContext(Dispatchers.IO) {
            if (isConnected()) {
                val articles = fetchArticleDataFromNetworkCoroutine(sharedPreferences.getString(Constants.COUNTRY_KEY,"in")!!)?.articles?.map {
                    it.toRoomResult()
                }
                deleteAllStories()
                saveArticleLocally(articles)
                articleLiveData.postValue(articles)
            } else {
                articleLiveData.postValue(fetchArticleDatafromRoom())
            }
        }
    }

    suspend fun fetchSearchQueryCoroutine (q: String){
        withContext(Dispatchers.IO){
            if(isConnected()){
                val queries= fetchSearchQuery(q)
            }
        }
    }

    private suspend fun fetchSearchQuery(query: String):ResponseNews?{
        val response=apiService.getSearchQuery(query)
        return if (response.isSuccessful)
        {
            Log.i("NewsRepository", response.toString())
            response.body()
        }

        else {
            Log.i("NewsRepository", "Error Fetching Data")
            null
        }
    }

    private suspend fun fetchArticleDatafromRoom(): List<ArticleRoom>? {
        return database.getArticlesDao().getArticles()
    }

    private suspend fun saveArticleLocally(articles: List<ArticleRoom>?) {
        database.getArticlesDao().insertArticle(articles)
    }

    private suspend fun deleteAllStories() {
        database.getArticlesDao().deleteAllArticles()
    }

    private suspend fun fetchArticleDataFromNetworkCoroutine(country: String): ResponseNews? {

        //return apiService.getTopHeadLines("in")
        val response = apiService.getTopHeadLines(country)
        return if (response.isSuccessful)
        {
            Log.i("NewsRepository", response.toString())
            response.body()
        }

        else {
            Log.i("NewsRepository", "Error Fetching Data")
            null
        }

    }
}