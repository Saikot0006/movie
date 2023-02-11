package com.example.movieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.BookmarkModel
import com.example.movieapp.model.Genre

@Database(entities = [Genre::class,BookmarkModel::class], version = 1)
abstract class MovieDB : RoomDatabase(){

    abstract fun getDao() : MovieDao

    companion object{
        @Volatile
        private  var db : MovieDB? = null

        fun getDB(context : Context) : MovieDB{
            if(db==null){
                synchronized(this){
                    db = Room.databaseBuilder(
                        context,
                        MovieDB::class.java,
                        "movie_db"
                    ).build()
                }

            }

            return db!!
        }
    }
}