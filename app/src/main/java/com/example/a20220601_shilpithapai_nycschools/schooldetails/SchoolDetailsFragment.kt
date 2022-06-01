package com.example.a20220601_shilpithapai_nycschools.schooldetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a20220601_shilpithapai_nycschools.R
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.databinding.FragmentSchoolDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "dbn"

class SchoolDetailsFragment : Fragment(R.layout.fragment_school_details) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentSchoolDetailsBinding? = null
    private val viewModel by viewModel<SchoolDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.getSchoolDetails(it.getString(ARG_PARAM1) ?: "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSchoolDetailsBinding.bind(view)

        observeData()



    }

    private fun observeData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    binding?.schoolName?.text = "School Name : ${it.value.school_name}"
                    binding?.mathScore?.text = "Math Score : ${it.value.sat_math_avg_score}"
                    binding?.readingScore?.text = "Reading Score : ${it.value.sat_critical_reading_avg_score}"
                    binding?.writingScore?.text = "Writing Score : ${it.value.sat_critical_reading_avg_score}"
                }
                else -> {

                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SchoolDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            SchoolDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}