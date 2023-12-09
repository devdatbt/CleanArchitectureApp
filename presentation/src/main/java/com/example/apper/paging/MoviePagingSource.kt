package com.example.apper.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasource.local.NoteDao
import com.example.domain.model.Note
import kotlinx.coroutines.delay

class MoviePagingSource (
    private val noteDao: NoteDao) :PagingSource<Int, Note>() {

    override fun getRefreshKey(state: PagingState<Int, Note>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        val page = params.key ?: 0
        Log.d("Datbt","page:  ${page}")
        Log.d("Datbt","params.loadSize:  ${params.loadSize}")
        return try {
            val entities = noteDao.getPagedList(params.loadSize, page * params.loadSize)
                .map {
                    Log.d("Datbt","AAAAA:  ${it.content}")
                    it.toNote()
                }
            // simulate page loading
            if (page != 0)
                delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}