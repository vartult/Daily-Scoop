package com.cellfishpool.news.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cellfishpool.news.database.NewsRepository
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: NewsRepository):BaseViewModel(){
    val searchLiveData: LiveData<List<ArticleRoom>> = Transformations.map(repository.searchLiveData){
        it
    }

    fun getData(query: String){
        viewModelScope.launch {
            repository.fetchSearchQuery(query)
        }

    }
}