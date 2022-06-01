package com.example.a20220601_shilpithapai_nycschools.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.a20220601_shilpithapai_nycschools.Constants.TOTAL_ITEM_TO_BE_DISPLAYED
import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import com.example.a20220601_shilpithapai_nycschools.models.School
import kotlinx.coroutines.flow.Flow

class SchoolListRepositoryImpl(private val apiService: ApiService) : SchoolListRepository {
    override suspend fun getSchools(): Flow<PagingData<School>> {
        return Pager(
            config = PagingConfig(
                pageSize = TOTAL_ITEM_TO_BE_DISPLAYED,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingDataSource(apiService) }
        ).flow
    }
}