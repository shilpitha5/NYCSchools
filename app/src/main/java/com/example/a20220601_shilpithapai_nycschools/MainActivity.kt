package com.example.a20220601_shilpithapai_nycschools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a20220601_shilpithapai_nycschools.databinding.ActivityMainBinding
import com.example.a20220601_shilpithapai_nycschools.schooldetails.SchoolDetailsFragment
import com.example.a20220601_shilpithapai_nycschools.schoollist.SchoolFragment

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchSchoolFragment()
    }

    fun launchSchoolFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerSchool, SchoolFragment())
            .commit()
    }

    // Given time, display both list and detail in single screen for tablet
    fun launchSchoolDetailsFragment(dbn: String) {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (isTablet) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerSchoolDetail, SchoolDetailsFragment.newInstance(dbn))
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerSchool, SchoolDetailsFragment.newInstance(dbn))
                .addToBackStack(null)
                .commit()
        }
    }
}