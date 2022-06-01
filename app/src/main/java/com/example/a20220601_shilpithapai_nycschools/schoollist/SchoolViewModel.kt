package com.example.a20220601_shilpithapai_nycschools.schoollist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolListRepository

class SchoolViewModel(private val schoolListRepository: SchoolListRepository) : ViewModel() {

    suspend fun getSchools() =
        schoolListRepository.getSchools().cachedIn(viewModelScope)

}
