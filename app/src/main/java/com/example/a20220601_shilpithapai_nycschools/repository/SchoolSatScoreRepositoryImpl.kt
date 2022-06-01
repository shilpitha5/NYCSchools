package com.example.a20220601_shilpithapai_nycschools.repository

import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class SchoolSatScoreRepositoryImpl(private val apiService: ApiService) : SchoolSatScoreRepository {
    override suspend fun getSchoolSatScore(dbn: String): Flow<ResultWrapper<SchoolSatScore>> =
        flow {
            try {
                val result = apiService.getSchoolSATScore(dbn)
                if (result.isSuccessful) {
                   emit( ResultWrapper.Success(result.body()!![0]))
                } else {
                    emit(ResultWrapper.NetworkError(result.code(), result.message()))
                }
            } catch (exception: IOException) {
                emit(ResultWrapper.Error(exception))
            }
        }
}