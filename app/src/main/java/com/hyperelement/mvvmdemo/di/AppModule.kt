package com.hyperelement.mvvmdemo.di

import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.ui.fragment.FragmentMoviesVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Inject ViewModels
    viewModel {
        FragmentMoviesVM(
            get()
        )
    }


    // Inject Repository
    single { FragmentOneRepository(androidContext(),get()) }

}
