package com.cellfishpool.news.ui.news

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.R
import com.cellfishpool.news.databinding.ActivityMainBinding
import com.cellfishpool.news.ui.base.BaseActivity
import com.cellfishpool.news.ui.search.SearchFragment
import com.cellfishpool.news.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class NewsActivity : BaseActivity<NewsViewModel, ActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NewsApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, TopNewsFragment())
                .commit()
        }

    }


    override fun getViewModelClass(): Class<NewsViewModel> = NewsViewModel::class.java

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {


        when (p0.itemId) {
            R.id.action_top -> {
                if(count!=0){
                    count = 0
                    Timber.d(p0.toString())
                    supportFragmentManager.beginTransaction().replace(R.id.container, TopNewsFragment())
                        .commit()
                }

            }
            R.id.action_search->{
                if(count!=1){
                    count = 1
                    supportFragmentManager.beginTransaction().replace(R.id.container,SearchFragment())
                        .commit()
                }
            }
            R.id.action_settings->{
                if(count!=2){
                    count = 2
                    //throw RuntimeException("Test Crash")
                    supportFragmentManager.beginTransaction().replace(R.id.container,SettingsFragment())
                        .commit()
                }
            }
        }
        return true
    }
}