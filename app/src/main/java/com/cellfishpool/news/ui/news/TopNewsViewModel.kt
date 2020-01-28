package com.cellfishpool.news.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellfishpool.news.database.NewsRepository
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopNewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel() {
    val newsLiveData: LiveData<List<ArticleRoom>> = Transformations.map(newsRepository.articleLiveData,{
        it
    })

    fun getData(){
        viewModelScope.launch {
            newsRepository.fetchArticleDataFromNetworkCoroutine()
        }
    }
}
