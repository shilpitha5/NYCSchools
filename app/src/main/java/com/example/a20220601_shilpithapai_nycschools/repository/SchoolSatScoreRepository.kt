package com.example.a20220601_shilpithapai_nycschools.repository

import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import kotlinx.coroutines.flow.Flow

interface SchoolSatScoreRepository {

    suspend fun getSchoolSatScore(dbn: String): Flow<ResultWrapper<List<SchoolSatScore>>>
}