package com.example.a20220601_shilpithapai_nycschools.schoollist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220601_shilpithapai_nycschools.MainActivity
import com.example.a20220601_shilpithapai_nycschools.R
import com.example.a20220601_shilpithapai_nycschools.databinding.FragmentSchoolBinding
import com.example.a20220601_shilpithapai_nycschools.util.Utils.isTablet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchoolFragment : Fragment(R.layout.fragment_school) {

    private var binding: FragmentSchoolBinding? = null
    private lateinit var schoolAdapter: SchoolListAdapter

    private val viewModel by viewModel<SchoolViewModel>()
    private val parentActivity by lazy {
        activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSchoolBinding.bind(view)
        setUpAdapter()
        observeData()
    }

    private fun setUpAdapter() {
        schoolAdapter = SchoolListAdapter(parentActivity::launchSchoolDetailsFragment)
        binding?.rvSchools?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolAdapter
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getSchools().collectLatest {
                schoolAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            schoolAdapter.loadStateFlow.distinctUntilChangedBy {
                it.refresh
            }.collect {
                // launches the detail fragment by default for first item in tablet
                if (schoolAdapter.snapshot().isNotEmpty())
                    if (isTablet(requireContext()))
                        parentActivity.launchSchoolDetailsFragment(schoolAdapter.snapshot()[0]!!)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            it.title = "Schools"
        }
    }
}
