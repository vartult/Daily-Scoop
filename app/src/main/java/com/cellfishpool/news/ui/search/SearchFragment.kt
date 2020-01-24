package com.cellfishpool.news.ui.search

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.SearchFragmentBinding
import com.cellfishpool.news.network.model.ArticleRoom
import com.cellfishpool.news.ui.base.BaseFragment
import com.cellfishpool.news.ui.news.TopNewsAdapter
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment : BaseFragment<SearchViewModel, SearchFragmentBinding>() {
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

//    override fun addListner() {
//        with(binding.searchView) {
//
//            setOnQueryTextListener(this@SearchFragment)
//            setOnFocusChangeListener { view, hasFocus ->
//                if (hasFocus) showKeyboard(view)
//                else dismissKeyboard(view)
//            }
//            requestFocus()
//        }
//    }

    override fun addListner() {
        with(binding.searchView) {
            setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.getData(searchView.text.toString())
                    dismissKeyboard(activity?.currentFocus!!)
                    true
                }
                false
            }
            showKeyboard(activity?.currentFocus!!)

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