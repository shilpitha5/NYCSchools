package com.example.a20220601_shilpithapai_nycschools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a20220601_shilpithapai_nycschools.databinding.ActivityMainBinding
import com.example.a20220601_shilpithapai_nycschools.models.School
import com.example.a20220601_shilpithapai_nycschools.schooldetails.SchoolDetailsFragment
import com.example.a20220601_shilpithapai_nycschools.schoollist.SchoolFragment
import com.example.a20220601_shilpithapai_nycschools.util.Utils.isTablet

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchSchoolFragment()
    }

    private fun launchSchoolFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSchool, SchoolFragment())
            .commit()
    }

    // Given time, display both list and detail in single screen for tablet
    fun launchSchoolDetailsFragment(school: School) {
        if (isTablet(this)) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainerSchoolDetail,
                    SchoolDetailsFragment.newInstance(school)
                )
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerSchool, SchoolDetailsFragment.newInstance(school))
                .addToBackStack(null)
                .commit()
        }
    }
}
