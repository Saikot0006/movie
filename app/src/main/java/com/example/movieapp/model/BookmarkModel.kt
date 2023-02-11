package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_bookmark")
data class BookmarkModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "bookmark_id")
    val bookmarkId : Long,
    val name : String,
    val runTime : String,
    @ColumnInfo(name = "genre")
    val genreList : String
)

