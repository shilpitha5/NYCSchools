package com.example.a20220601_shilpithapai_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.helper.DataDummy.generateDummySatScore
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolSatScoreRepository
import com.example.a20220601_shilpithapai_nycschools.schooldetails.SchoolDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SchoolDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = Dispatchers.Unconfined
    private val testScope = TestScope()
    private val schoolSatScoreRepository: SchoolSatScoreRepository = mockk()
    private lateinit var schoolDetailsViewModel: SchoolDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        schoolDetailsViewModel = SchoolDetailsViewModel((schoolSatScoreRepository))
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given school with sat score when getSchoolDetails called then returns the Sat Scores`() =
        testScope.runTest {
            // Given
            val list = listOf(
                generateDummySatScore(dbn = "123", schoolName = "School Name1")
            )
            coEvery {
                schoolSatScoreRepository.getSchoolSatScore("123")
            } coAnswers {
                flow { emit(ResultWrapper.Success(list)) }
            }

            // When
            schoolDetailsViewModel.getSchoolDetails("123")

            // Then
            assertEquals(ResultWrapper.Success(list), schoolDetailsViewModel.satScoreLiveData.value)
        }

    @Test
    fun `Given school without sat score when getSchoolDetails called then returns null`() =
        testScope.runTest {
            // Given
            val list = listOf<SchoolSatScore>()
            coEvery {
                schoolSatScoreRepository.getSchoolSatScore("123")
            } coAnswers {
                flow { emit(ResultWrapper.Success(list)) }
            }

            // When
            schoolDetailsViewModel.getSchoolDetails("123")

            // Then
            assertEquals(ResultWrapper.Success(list), schoolDetailsViewModel.satScoreLiveData.value)
        }

    @Test
    fun `Given invalid when getSchoolDetails called then returns null`() =
        testScope.runTest {
            // Given
            coEvery {
                schoolSatScoreRepository.getSchoolSatScore("123")
            } coAnswers {
                flow { emit(ResultWrapper.NetworkError(500, "Some Error")) }
            }

            // When
            schoolDetailsViewModel.getSchoolDetails("123")

            // Then
            assertTrue(schoolDetailsViewModel.satScoreLiveData.value is ResultWrapper.NetworkError)
        }
}
