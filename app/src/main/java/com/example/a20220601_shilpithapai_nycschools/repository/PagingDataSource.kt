package com.example.a20220601_shilpithapai_nycschools.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a20220601_shilpithapai_nycschools.Constants.PAGE_SIZE
import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import com.example.a20220601_shilpithapai_nycschools.models.School
import okio.IOException

class PagingDataSource(private val apiService: ApiService) : PagingSource<Int, School>() {
    override fun getRefreshKey(state: PagingState<Int, School>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, School> {
        val position = params.key ?: 1
        val offset = if (params.key == null) (position - 1) * PAGE_SIZE else 0

        return try {
            val response = apiService.getSchools(offset, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else (params.loadSize / PAGE_SIZE) + position
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}