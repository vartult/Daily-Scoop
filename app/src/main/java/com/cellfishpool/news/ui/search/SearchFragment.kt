package com.cellfishpool.news.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.SearchFragmentBinding
import com.cellfishpool.news.network.model.ArticleX
import com.cellfishpool.news.ui.base.BaseFragment
import com.jakewharton.rxbinding2.widget.RxTextView

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>() {
    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getLayoutId(): Int = R.layout.search_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun initRecycler() {
        with(binding.searchrecyclerview) {
            adapter = SearchAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun addListner() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            stopRefreshing()
        }

        with(binding.searchView) {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val observableQuery = RxTextView.textChanges(this@with).map { it.toString() }
                    viewModel.autoResult(observableQuery)
                    if (s.toString().length >= 3)
                        this@SearchFragment.startRefreshing()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
            setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    dismissKeyboard(activity?.currentFocus!!)
                }
                false
            }

            showKeyboard(activity?.currentFocus!!)
        }
    }


    private fun updateRecyclerViewAdapter(results: PagedList<ArticleX>) {
        stopRefreshing()
        val adapter = binding.searchrecyclerview.adapter
        (adapter as? SearchAdapter)?.submitList(results)
    }

    override fun addObservers() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            updateRecyclerViewAdapter(it)
        })
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