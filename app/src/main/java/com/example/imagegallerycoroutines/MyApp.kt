package com.example.imagegallerycoroutines

import android.app.Application
import com.example.imagegallerycoroutines.di.modules.repositoryModule
import com.example.imagegallerycoroutines.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appComponent)
        }
    }

    private val appComponent = listOf(
        repositoryModule,
        viewModelModule,
    )
}