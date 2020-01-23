package com.cellfishpool.news

import android.app.Application
import com.cellfishpool.news.di.AppComponent
import com.cellfishpool.news.di.DaggerAppComponent

class NewsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}