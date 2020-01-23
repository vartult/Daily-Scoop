package com.cellfishpool.news.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cellfishpool.news.database.NewsRepository
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel() {

}