package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapp.dao.MovieDao
import com.example.movieapp.db.MovieDB
import com.example.movieapp.model.BookmarkModel
import com.example.movieapp.repository.BookMarkRepository
import com.example.movieapp.repository.MovieDetailsRepository
import kotlinx.coroutines.launch

class BookMarkViewModel(application: Application)
    : AndroidViewModel(application) {
    private lateinit var dao: MovieDao
    private lateinit var repository: BookMarkRepository
    init {
        dao = MovieDB.getDB(application).getDao()
        repository = BookMarkRepository(dao)
    }

    fun getBookMarkMovie() : LiveData<List<BookmarkModel>> {
        return repository.getBookMarkMovie()
    }

    fun deleteBookMarks(id: Long){
        viewModelScope.launch {
            try {
                repository.deleteBookMarks(id)
            }catch (e:Exception){}
        }
    }
}