package com.cellfishpool.news.network.model

fun ArticleX.toRoomResult() : ArticleRoom{
    return ArticleRoom(
        title,
        description,
        urlToImage
    )
}