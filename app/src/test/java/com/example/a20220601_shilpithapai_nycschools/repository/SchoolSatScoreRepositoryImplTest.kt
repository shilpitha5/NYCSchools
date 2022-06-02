package com.example.a20220601_shilpithapai_nycschools.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SchoolSatScoreRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = Dispatchers.Unconfined

    private val apiService: ApiService = mockk()
    var repository: SchoolSatScoreRepositoryImpl = SchoolSatScoreRepositoryImpl(apiService)


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = SchoolSatScoreRepositoryImpl(apiService)
    }

    @Test
    fun `When getSchoolSatScore is called, then the getSchoolSatSc`() {
        runTest {
            // When
            repository.getSchoolSatScore("123").collectLatest { }

            // Then
            coVerify { apiService.getSchoolSATScore("123") }
        }
    }
}
