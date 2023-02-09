package com.example.movieapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.Genre
import com.example.movieapp.model.GenreModel

@Dao
interface MovieDao {
    @Insert
    suspend fun insertGenre(genre: List<Genre>)

}