package com.example.a20220601_shilpithapai_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.a20220601_shilpithapai_nycschools.helper.DataDummy.generateDummySchool
import com.example.a20220601_shilpithapai_nycschools.helper.Utils.getOrAwaitValue
import com.example.a20220601_shilpithapai_nycschools.helper.Utils.setupDiffer
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolListRepository
import com.example.a20220601_shilpithapai_nycschools.schoollist.SchoolViewModel
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SchoolViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = Dispatchers.Unconfined
    private val testScope = TestScope()
    private val schoolListRepository: SchoolListRepository = mockk()
    private lateinit var schoolViewModel: SchoolViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        schoolViewModel = SchoolViewModel((schoolListRepository))
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Gets list of schools`() = testScope.runTest {

        // Given
        val schoolList = listOf(
            generateDummySchool(dbn = "123", schoolName = "School Name1"),
            generateDummySchool(dbn = "456", schoolName = "School Name2")
        )
        coEvery { schoolListRepository.getSchools() } coAnswers {
            flow { emit(PagingData.from(schoolList)) }
        }

        // When
        val schoolLivedata = schoolViewModel.getSchools().asLiveData()
        val differ = setupDiffer().apply {
            submitData(schoolLivedata.getOrAwaitValue())
        }

        // Then
        assertEquals(schoolList, differ.snapshot().items)
        assertEquals(schoolList.size, differ.snapshot().items.size)
    }
}

