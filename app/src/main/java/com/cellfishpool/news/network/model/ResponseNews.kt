package com.cellfishpool.news.network.model

data class ResponseNews(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)