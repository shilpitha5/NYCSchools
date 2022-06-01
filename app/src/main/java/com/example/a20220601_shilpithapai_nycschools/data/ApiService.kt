package com.example.a20220601_shilpithapai_nycschools.data

import com.example.a20220601_shilpithapai_nycschools.models.School
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$offset") offset: Int,
        @Query("\$limit") limit: Int
    ): List<School>

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolSATScore(
        @Query("dbn") dbn: String
    ): Response<List<SchoolSatScore>>
}
