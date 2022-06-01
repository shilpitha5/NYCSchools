package com.example.a20220601_shilpithapai_nycschools.repository

import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SchoolSatScoreRepositoryImpl(private val apiService: ApiService) : SchoolSatScoreRepository {
    override suspend fun getSchoolSatScore(dbn: String): Flow<ResultWrapper<List<SchoolSatScore>>> =
        flow {
            emit(ResultWrapper.Loading)
            kotlin.runCatching {
                apiService.getSchoolSATScore(dbn)
            }.onSuccess {
                if (it.isSuccessful) {
                    emit(ResultWrapper.Success(it.body()!!))
                } else {
                    emit(ResultWrapper.NetworkError(it.code(), it.message()))
                }
            }.onFailure {
                emit(ResultWrapper.Error(it))
            }

        }
}