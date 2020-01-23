package com.cellfishpool.news

import android.app.Application
import com.cellfishpool.news.di.AppComponent
import com.cellfishpool.news.di.DaggerAppComponent
import timber.log.Timber

class NewsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}