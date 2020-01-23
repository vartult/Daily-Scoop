package com.cellfishpool.news.ui.search

import android.os.Bundle
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.SearchFragmentBinding
import com.cellfishpool.news.ui.base.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>() {
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getLayoutId(): Int= R.layout.search_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun initRecycler() {

    }

    override fun addListner() {
    }

    override fun addObservers() {
    }

}