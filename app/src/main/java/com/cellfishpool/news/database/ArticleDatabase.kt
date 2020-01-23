package com.cellfishpool.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cellfishpool.news.network.model.ArticleRoom

@Database(entities = [ArticleRoom::class],version = 1,exportSchema = false)
abstract class ArticleDatabase : RoomDatabase(){
    companion object{
        private var APPDATABASE : ArticleDatabase?=null

        fun getIntance(context: Context): ArticleDatabase?{
            if (APPDATABASE == null) {
                synchronized(ArticleDatabase::class) {
                    APPDATABASE = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java, "articleDatabase.db"
                    ).build()
                }
            }
            return APPDATABASE
        }
    }


    abstract fun getArticlesDao(): ArticleDao

}