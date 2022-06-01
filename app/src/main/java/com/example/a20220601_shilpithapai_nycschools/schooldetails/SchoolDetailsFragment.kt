package com.example.a20220601_shilpithapai_nycschools.schooldetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.a20220601_shilpithapai_nycschools.R
import com.example.a20220601_shilpithapai_nycschools.data.ResultWrapper
import com.example.a20220601_shilpithapai_nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.a20220601_shilpithapai_nycschools.models.School
import com.example.a20220601_shilpithapai_nycschools.util.Constants.KEY_SCHOOL
import com.example.a20220601_shilpithapai_nycschools.util.Utils.isTablet
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
        if (!isTablet(requireContext())) activity?.title = "School Details"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSchoolDetailsBinding.bind(view)
        observeData()
        viewModel.getSchoolDetails(school.dbn)
        setUpView()
        setClickListeners()
    }

    private fun setClickListeners() = binding?.run {
        location.setOnClickListener {
            val uri =
                Uri.parse("http://maps.google.com/maps?q=${school.latitude},${school.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.apply {
                setPackage("com.google.android.apps.maps")
                startActivity(this)
            }
        }
    }

    private fun setUpView() = binding?.run {
        schoolName.text = school.school_name
        contactNo.text = school.phone_number
        email.text = school.school_email
        website.text = school.website
        schoolOverview.text = school.overview_paragraph
    }

    private fun observeData() {
        viewModel.satScoreLiveData.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it is ResultWrapper.Loading
            when (it) {
                is ResultWrapper.Success -> {
                    val satScore = it.value
                    val notAvailable = getString(R.string.not_available)
                    binding?.mathScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_math_avg_score
                    binding?.readingScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_critical_reading_avg_score
                    binding?.writingScore?.text =
                        if (satScore.isEmpty()) notAvailable else satScore[0].sat_writing_avg_score
                }
                is ResultWrapper.NetworkError -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(
                        context,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_LONG
                    ).show()

                }
                else -> {}
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
