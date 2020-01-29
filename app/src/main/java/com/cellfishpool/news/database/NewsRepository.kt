package com.cellfishpool.news.database

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cellfishpool.news.R
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.network.model.ArticleX
import com.cellfishpool.news.network.model.ResponseNews
import com.cellfishpool.news.network.model.toRoomResult
import com.cellfishpool.news.network.service.ApiService
import com.cellfishpool.news.utils.Constants
import com.cellfishpool.news.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: ArticleDatabase,
    private val networkUtil: NetworkUtil,
    private val sharedPreferences: SharedPreferences
) {

    //val progress
    val articleLiveData = MutableLiveData<List<ArticleRoom>>()
    private lateinit var sourceFactory: SearchDatasourceFactory

    val searchQueryLiveData= MutableLiveData<String>()

    private val pageListConfig: PagedList.Config by lazy{
        val pageSize=1
        PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .build()
    }

    val searchLiveData: LiveData<PagedList<ArticleX>> = Transformations.switchMap(searchQueryLiveData){
        sourceFactory = SearchDatasourceFactory(it?:"",apiService)
        LivePagedListBuilder<Int,ArticleX>(sourceFactory,pageListConfig).build()
    }


    private fun isConnected(): Boolean {
        return networkUtil.isNetworkAvailable()
    }

    suspend fun fetchArticleDataCoroutine(responseNews: ResponseNews) {
        withContext(Dispatchers.IO) {
            if (isConnected()) {
                val articles = responseNews.articles.map {
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

//    private suspend fun fetchSearchQueryCoroutine (responseNews: ResponseNews){
//        withContext(Dispatchers.IO){
//            if(isConnected()){
//                val queries= responseNews.articles.map {
//                    it.toRoomResult()
//                }
//                searchLiveData.postValue(queries)
//            }
//        }
//    }

//     suspend fun fetchSearchQuery(query: String){
//        val response=apiService.getSearchQuery(query,Constants.API_KEY,)
//         if (response.isSuccessful)
//        {
//            Log.i("NewsRepository", response.toString())
//            fetchSearchQueryCoroutine(response.body()!!)
//        }
//
//        else {
//            Log.i("NewsRepository", "Error Fetching Data")
//
//        }
//    }
    fun fetchSearchQuery(query: String){
    searchQueryLiveData.value=query
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

    suspend fun fetchArticleDataFromNetworkCoroutine(){
        val country=sharedPreferences.getString(Constants.COUNTRY_KEY,"in")!!
        //return apiService.getTopHeadLines("in")
        val response = apiService.getTopHeadLines(country)
        if (response.isSuccessful)
        {
            Timber.i(response.toString())
            fetchArticleDataCoroutine(response.body()!!)
        }
        else {
           Timber.i("Error Fetching Data")

        }

    }
}