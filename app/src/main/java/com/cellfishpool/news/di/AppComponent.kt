package com.cellfishpool.news.di

import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.ui.news.NewsActivity
import com.cellfishpool.news.ui.search.SearchFragment
import com.cellfishpool.news.ui.news.TopNewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: NewsApplication): AppComponent
    }

    fun inject(newsActivity: NewsActivity)
    fun inject(topNewsFragment: TopNewsFragment)
    fun inject(SearchFragment: SearchFragment)
}