package com.example.imagegallerycoroutines.data.repository

import android.graphics.Bitmap
import org.json.JSONObject

interface ImageAPI {

    suspend fun getRandomImage(): Bitmap?

    suspend fun getRandomImageJsonString(): Bitmap?


    suspend fun getRandomImageJsonString(url: String): Bitmap?

    suspend fun ImageJsonToBitmap(imageJson: JSONObject): Bitmap?
}