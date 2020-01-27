package com.cellfishpool.news.ui.news

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.TopNewsFragmentBinding
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseFragment

class TopNewsFragment : BaseFragment<TopNewsViewModel, TopNewsFragmentBinding>() {

    override fun getViewModelClass(): Class<TopNewsViewModel> = TopNewsViewModel::class.java

    override fun getLayoutId(): Int = R.layout.top_news_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startRefreshing()
        viewModel.getData()
    }

    override fun initRecycler() {
        with(binding.recyclerView) {
            adapter = TopNewsAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun addListner() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getData()
        }
    }

    override fun addObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            stopRefreshing()
            updateRecyclerViewAdapter(it)
        })
    }

    private fun updateRecyclerViewAdapter(results: List<ArticleRoom>) {
        val adapter = binding.recyclerView.adapter

        (adapter as? TopNewsAdapter)?.updateDataSet(results)

    }

    override fun stopRefreshing() {
        with(binding.swipeRefreshLayout) {
            if (isRefreshing) {
                isRefreshing = false
            }
        }
    }

    override fun startRefreshing() {
        with(binding.swipeRefreshLayout) {
            if (!isRefreshing) {
                isRefreshing = true
            }
        }
    }

}
