package com.cellfishpool.news.database

import androidx.room.*
import com.cellfishpool.news.network.model.ArticleRoom

@Dao
interface ArticleDao {
    @Query("SELECT * FROM Article")
    suspend fun getArticles(): List<ArticleRoom>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArticle(result: ArticleRoom)

    @Insert
    suspend fun insertArticle(result: List<ArticleRoom>?)

    @Delete
    suspend fun deleteArticle(result: ArticleRoom)

    @Query("DELETE FROM Article")
    suspend fun deleteAllArticles()
}