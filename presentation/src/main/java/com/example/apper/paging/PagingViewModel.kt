package com.example.apper.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.datasource.local.NoteDao

class PagingViewModel(
    private val dao: NoteDao
) : ViewModel() {
    val data = Pager(
        PagingConfig(
            pageSize = 2,
            enablePlaceholders = false,
            initialLoadSize = 1
        ),
    ) {
        MoviePagingSource(dao)
    }.flow.cachedIn(viewModelScope)
}

class MainViewModelFactory(
    private val dao: NoteDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PagingViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}