package com.cellfishpool.news.database

import androidx.paging.PageKeyedDataSource
import com.cellfishpool.news.network.model.ArticleX
import com.cellfishpool.news.network.service.ApiService
import com.cellfishpool.news.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchDatasource(
    private val searchQuery: String,
    private val apiService: ApiService
) : PageKeyedDataSource<Int, ArticleX>() {
    private val networkScope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleX>
    ) {
        val key = params.requestedLoadSize
        networkScope.launch {
            val result = apiService.getSearchQuery(searchQuery, Constants.API_KEY, key)
            Timber.d(result.toString())
            if(result.body()!=null)
            callback.onResult(result.body()!!.articles, 0, key + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleX>) {
        networkScope.launch {
            val result = apiService.getSearchQuery(searchQuery, Constants.API_KEY, params.key)
            if(result.body()!=null)
            callback.onResult(result.body()!!.articles, params.key + 1)
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleX>) {

    }

}