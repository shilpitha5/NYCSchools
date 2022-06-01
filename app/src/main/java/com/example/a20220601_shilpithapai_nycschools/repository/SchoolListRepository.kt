package com.example.a20220601_shilpithapai_nycschools.repository

import androidx.paging.PagingData
import com.example.a20220601_shilpithapai_nycschools.models.School
import kotlinx.coroutines.flow.Flow

interface SchoolListRepository {

    suspend fun getSchools(): Flow<PagingData<School>>
}