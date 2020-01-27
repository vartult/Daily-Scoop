package com.cellfishpool.news.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cellfishpool.news.database.NewsRepository
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: NewsRepository) :
    BaseViewModel() {

    val searchLiveData: LiveData<List<ArticleRoom>> =
        Transformations.map(repository.searchLiveData) {
            it
        }

    fun getData(query: String) {
        viewModelScope.launch {
            repository.fetchSearchQuery(query)
        }

    }

    @SuppressLint("CheckResult")
    fun autoResult(observableQuery: Observable<String>) {
        observableQuery.debounce(400, TimeUnit.MILLISECONDS)
            .filter { query ->
                !query.isEmpty() && query.length >= 3
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                getData(query)
            }
    }
}