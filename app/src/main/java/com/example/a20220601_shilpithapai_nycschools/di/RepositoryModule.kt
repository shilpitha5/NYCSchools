package com.example.a20220601_shilpithapai_nycschools.di

import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolListRepository
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolListRepositoryImpl
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolSatScoreRepository
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolSatScoreRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { provideSchoolRepository(get()) }
    factory { provideSchoolSatScoreRepository(get()) }
}

fun provideSchoolRepository(apiService: ApiService): SchoolListRepository {
    return SchoolListRepositoryImpl(apiService)
}

fun provideSchoolSatScoreRepository(apiService: ApiService): SchoolSatScoreRepository {
    return SchoolSatScoreRepositoryImpl(apiService)
}
