package com.example.imagegallerycoroutines.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagegallerycoroutines.data.repository.ImageApiImpl

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(private val repository: ImageApiImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}