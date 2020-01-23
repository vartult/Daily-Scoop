package com.cellfishpool.news.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class ArticleRoom(
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name= "Description")
    val description: String?,
    @ColumnInfo(name = "url")
    val urlToImage: String?

){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}