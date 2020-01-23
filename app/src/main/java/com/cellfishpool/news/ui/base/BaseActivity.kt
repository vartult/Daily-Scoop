package com.cellfishpool.news.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: DB

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(getLayoutId())
    }

    private fun bindView(layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    abstract fun getLayoutId(): Int
}