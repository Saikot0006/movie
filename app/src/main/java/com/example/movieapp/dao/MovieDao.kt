package com.example.movieapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.BookmarkModel
import com.example.movieapp.model.Genre
import com.example.movieapp.model.GenreModel

@Dao
interface MovieDao {
    @Insert
    suspend fun insertGenre(genre: List<Genre>)
    @Insert
    suspend fun insertBookMarks(bookmarkModel: BookmarkModel)

    @Query("select bookmark_id from tbl_bookmark where bookmark_id = :bookmarkId")
    fun getMovieById(bookmarkId : Long) : LiveData<Boolean>

    @Query("select * from tbl_bookmark")
    fun getBookMarkMovie() : LiveData<List<BookmarkModel>>


}