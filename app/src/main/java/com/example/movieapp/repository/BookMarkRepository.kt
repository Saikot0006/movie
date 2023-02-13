package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.model.BookmarkModel

class BookMarkRepository(private val dao: MovieDao) {

    fun getBookMarkMovie() : LiveData<List<BookmarkModel>> = dao.getBookMarkMovie()

    suspend fun deleteBookMarks(id: Long){
        try {
            dao.deleteBookMarks(id)
        }catch (e:Exception){}
    }


}