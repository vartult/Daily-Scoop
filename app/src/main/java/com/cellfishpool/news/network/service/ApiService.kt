package com.cellfishpool.news.network.service

import com.cellfishpool.news.network.model.ResponseNews
import com.cellfishpool.news.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<ResponseNews>

    @GET("everything")
    suspend fun getSearchQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int
    ): Response<ResponseNews>
}