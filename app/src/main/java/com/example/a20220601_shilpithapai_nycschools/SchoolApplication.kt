package com.example.a20220601_shilpithapai_nycschools

import android.app.Application
import com.example.a20220601_shilpithapai_nycschools.di.networkModule
import com.example.a20220601_shilpithapai_nycschools.di.repositoryModule
import com.example.a20220601_shilpithapai_nycschools.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SchoolApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SchoolApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }

}