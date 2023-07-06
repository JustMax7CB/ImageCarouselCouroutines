package com.example.imagegallerycoroutines.di.modules

import com.example.imagegallerycoroutines.data.repository.ImageApiImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ImageApiImpl() }
}