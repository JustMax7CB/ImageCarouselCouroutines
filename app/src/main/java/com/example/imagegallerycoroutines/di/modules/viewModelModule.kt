package com.example.imagegallerycoroutines.di.modules

import com.example.imagegallerycoroutines.viewmodel.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MainActivityViewModel> { MainActivityViewModel(repository = get()) }
}