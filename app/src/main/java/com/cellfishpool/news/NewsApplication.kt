package com.cellfishpool.news

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
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

        var value=appComponent.getSharedPreference().getBoolean(getString(R.string.dark_key),false)
        if(value)
        {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        }
        else{
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }

    }

}