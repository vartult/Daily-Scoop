package com.cellfishpool.news.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.internal.operators.flowable.BlockingFlowableIterable
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModel, B: ViewDataBinding> : Fragment() {

    lateinit var viewModel: VM
    lateinit var binding: B

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,getLayoutId(),container, false)
        bindView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        addListner()
        addObservers()

    }

    private fun bindView()
    {
        viewModel= ViewModelProvider(this,viewModelFactory).get(getViewModelClass())
    }
    abstract fun getViewModelClass():Class<VM>

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun initRecycler()
    abstract fun addListner()
    abstract fun addObservers()
}