package com.example.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class GenreModel(
    val genres: List<Genre>
)

@Entity(tableName = "tbl_genre")
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
)