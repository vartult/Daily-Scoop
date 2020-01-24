package com.cellfishpool.news.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.SearchFragmentBinding
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseFragment
import com.cellfishpool.news.ui.news.TopNewsAdapter

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>(),
    SearchView.OnQueryTextListener {
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getLayoutId(): Int = R.layout.search_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun initRecycler() {
        with(binding.searchrecyclerview) {
            adapter = TopNewsAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun addListner() {
        with(binding.searchView) {
            setOnQueryTextListener(this@SearchFragment)
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) showKeyboard(view)
                else dismissKeyboard(view)
            }
            requestFocus()
        }
    }

    private fun updateRecyclerViewAdapter(results: List<ArticleRoom>) {
        val adapter = binding.searchrecyclerview.adapter

        (adapter as? TopNewsAdapter)?.updateDataSet(results)

    }

    override fun addObservers() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            updateRecyclerViewAdapter(it)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getData(query!!)
        dismissKeyboard(activity?.currentFocus!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun showKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    private fun dismissKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}