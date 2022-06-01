package com.example.a20220601_shilpithapai_nycschools.di

import com.example.a20220601_shilpithapai_nycschools.schooldetails.SchoolDetailsViewModel
import com.example.a20220601_shilpithapai_nycschools.schoollist.SchoolViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SchoolViewModel(get()) }
    viewModel { SchoolDetailsViewModel(get()) }
}
