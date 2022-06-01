package com.example.a20220601_shilpithapai_nycschools.data

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val value: T) : ResultWrapper<T>()
    data class NetworkError(val code: Int, val message: String) : ResultWrapper<Nothing>()
    data class Error(val exception: Exception) : ResultWrapper<Nothing>()
}