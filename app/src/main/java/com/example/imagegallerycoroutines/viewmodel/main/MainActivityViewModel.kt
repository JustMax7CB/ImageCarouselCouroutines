package com.example.imagegallerycoroutines.viewmodel.main

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagegallerycoroutines.data.repository.ImageApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(val repository: ImageApiImpl) : ViewModel() {

    private val TAG = "MainActivityVM"

    private lateinit var job: Job

    private val imageBitmap = MutableLiveData<Bitmap?>()
    val readImage: MutableLiveData<Bitmap?> get() = imageBitmap

    init {
        Log.d(TAG, "ViewModel Created")
        getRandomImage()
    }

    fun getRandomImage() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val randomImage = repository.getRandomImage()
                if (randomImage != null) {
                    imageBitmap.postValue(randomImage)
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}