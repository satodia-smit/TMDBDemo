package com.hyperelement.mvvmdemo.di

import com.google.gson.Gson
import com.hyperelement.mvvmdemo.BuildConfig
import com.hyperelement.mvvmdemo.data.datasources.local.SearchAPIService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single { provideRetrofit(get(), get()) }
    single { provideSearchAPIService(get()) }
}

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideSearchAPIService(retrofit: Retrofit): SearchAPIService =
    retrofit.create(SearchAPIService::class.java)