package com.example.a20220601_shilpithapai_nycschools.di

import com.example.a20220601_shilpithapai_nycschools.BuildConfig
import com.example.a20220601_shilpithapai_nycschools.Constants
import com.example.a20220601_shilpithapai_nycschools.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideAPIService(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideAPIService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}