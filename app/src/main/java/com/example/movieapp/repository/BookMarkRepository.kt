package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.BookmarkModel

class BookMarkRepository(var dao: MovieDao) {

    fun getBookMarkMovie() : LiveData<List<BookmarkModel>> = dao.getBookMarkMovie()
}