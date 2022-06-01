package com.example.a20220601_shilpithapai_nycschools.schooldetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a20220601_shilpithapai_nycschools.Constants.KEY_SCHOOL
import com.example.a20220601_shilpithapai_nycschools.R
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.a20220601_shilpithapai_nycschools.models.School
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchoolDetailsFragment : Fragment(R.layout.fragment_school_details) {

    private var binding: FragmentSchoolDetailsBinding? = null
    private val viewModel by viewModel<SchoolDetailsViewModel>()
    private lateinit var school: School

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            school = it.getSerializable(KEY_SCHOOL) as School
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSchoolDetailsBinding.bind(view)
        observeData()
        viewModel.getSchoolDetails(school.dbn)
    }

    private fun observeData() {
        viewModel.satScoreLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    val satScore = it.value
                    val notAvailable = getString(R.string.not_available)
                    binding?.schoolName?.text = school.school_name
                    binding?.mathScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_math_avg_score
                    binding?.readingScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_math_avg_score
                    binding?.writingScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_math_avg_score
                }
                else -> {

                }
            }
        }
    }

    companion object {
        /**
         * @param school - Contains all the details of the school.
         * @return A new instance of fragment SchoolDetailsFragment.
         */
        @JvmStatic
        fun newInstance(school: School) =
            SchoolDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_SCHOOL, school)
                }
            }
    }
}
