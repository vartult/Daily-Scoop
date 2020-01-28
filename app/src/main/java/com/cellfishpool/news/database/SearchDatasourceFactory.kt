package com.cellfishpool.news.database

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cellfishpool.news.network.model.ArticleX
import com.cellfishpool.news.network.service.ApiService


class SearchDatasourceFactory(
    val searchQuery: String,
   val apiService: ApiService
) : DataSource.Factory<Int, ArticleX>() {
    private val datasourseLiveData = MutableLiveData<SearchDatasource>()
    override fun create(): DataSource<Int, ArticleX> {
        val datasource = SearchDatasource(searchQuery,apiService)
        datasourseLiveData.postValue(datasource)

        return datasource
    }
}