package com.example.a20220601_shilpithapai_nycschools.helper

import com.example.a20220601_shilpithapai_nycschools.models.School
import com.example.a20220601_shilpithapai_nycschools.models.SchoolSatScore

object DataDummy {

    fun generateDummySchool(dbn: String = "", schoolName: String = "") =
        School(
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            dbn,
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            schoolName,
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test",
            "test"
        )

    fun generateDummySatScore(dbn: String = "", schoolName: String = "") =
        SchoolSatScore(
            dbn,
            "test",
            "test",
            "test",
            "test",
            schoolName
        )
}
