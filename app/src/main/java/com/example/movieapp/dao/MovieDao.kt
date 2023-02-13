package com.example.movieapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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

    @Query("delete from tbl_bookmark where bookmark_id = :bookmarkId")
    suspend fun deleteBookMarks(bookmarkId : Long)

    @Query("select * from tbl_bookmark where bookmark_id = :bookmarkId")
    suspend fun getMovieById(bookmarkId : Long) : BookmarkModel?

    @Query("select * from tbl_bookmark")
    fun getBookMarkMovie() : LiveData<List<BookmarkModel>>

    @Query("select * from tbl_genre where id = :id")
    suspend fun getGenreDataByID(id : Int) : List<Genre>

    @Query("select * from tbl_genre")
    suspend fun getAllGenre() : List<Genre>


}