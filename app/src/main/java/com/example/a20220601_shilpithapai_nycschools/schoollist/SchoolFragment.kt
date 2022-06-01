package com.example.a20220601_shilpithapai_nycschools.schoollist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220601_shilpithapai_nycschools.MainActivity
import com.example.a20220601_shilpithapai_nycschools.R
import com.example.a20220601_shilpithapai_nycschools.databinding.FragmentSchoolBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchoolFragment : Fragment(R.layout.fragment_school) {

    private var binding: FragmentSchoolBinding? = null
    private lateinit var schoolAdapter: SchoolListAdapter

    private val viewModel by viewModel<SchoolViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSchoolBinding.bind(view)
        val parentActivity=activity as MainActivity
        schoolAdapter = SchoolListAdapter(parentActivity::launchSchoolDetailsFragment)
        binding?.rvSchools?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolAdapter
        }
        lifecycleScope.launch {
            viewModel.getSchools().collectLatest {
                schoolAdapter.submitData(it)
            }
        }
    }

}