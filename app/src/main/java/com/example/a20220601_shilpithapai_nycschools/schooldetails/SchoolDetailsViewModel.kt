package com.example.a20220601_shilpithapai_nycschools.schooldetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import com.example.a20220601_shilpithapai_nycschools.repository.SchoolSatScoreRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SchoolDetailsViewModel(private val schoolSatScoreRepository: SchoolSatScoreRepository) :
    ViewModel() {

    private var _resultLiveData: MutableLiveData<ResultWrapper<List<SchoolSatScore>>> =
        MutableLiveData()
    val resultLiveData
        get() = _resultLiveData

    fun getSchoolDetails(dbn: String) = viewModelScope.launch {
        schoolSatScoreRepository.getSchoolSatScore(dbn).collectLatest {
            _resultLiveData.postValue(it)
        }
    }

}