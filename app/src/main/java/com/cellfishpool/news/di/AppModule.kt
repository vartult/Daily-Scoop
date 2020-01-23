package com.cellfishpool.news.di

import android.content.ContentProvider
import android.content.Context
import com.cellfishpool.news.NewsApplication
import com.cellfishpool.news.database.ArticleDatabase
import com.cellfishpool.news.network.service.ApiService
import com.cellfishpool.news.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: NewsApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient,
                        coroutineCallAdapterFactory: CoroutineCallAdapterFactory): Retrofit{

        return Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideCoroutineFactory(): CoroutineCallAdapterFactory{
        return CoroutineCallAdapterFactory()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ArticleDatabase{
        return ArticleDatabase.getIntance(context)!!
    }
}