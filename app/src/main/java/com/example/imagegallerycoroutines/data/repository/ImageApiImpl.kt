package com.example.imagegallerycoroutines.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.imagegallerycoroutines.utils.ACCESS_KEY
import com.example.imagegallerycoroutines.utils.API_BASE_URL
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class ImageApiImpl : ImageAPI {

    val TAG = this.javaClass.name
    private val gSon: Gson = Gson()

    override suspend fun getRandomImage(): Bitmap? {
        return getRandomImageJsonString()
    }

    override suspend fun getRandomImageJsonString(): Bitmap? {
        val url = URL("${API_BASE_URL}photos/random?client_id=$ACCESS_KEY")
        val connection = url.openConnection() as HttpURLConnection
        with(connection) {
            setRequestProperty("Accept", "application/json")
            requestMethod = "GET"
        }
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val response = connection.inputStream.bufferedReader().readLine()
            val jsonObject = JSONTokener(response).nextValue() as JSONObject
            Log.d(TAG, jsonObject.toString())
            return ImageJsonToBitmap(jsonObject)
        }
        return null
    }

    override suspend fun getRandomImageJsonString(urlString: String): Bitmap? {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        with(connection) {
            setRequestProperty("Accept", "application/json")
            requestMethod = "GET"
        }
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val response = connection.inputStream.bufferedReader().readLine()
            val jsonObject = JSONTokener(response).nextValue() as JSONObject
            Log.d(TAG, jsonObject.toString())
            return ImageJsonToBitmap(jsonObject)
        }
        return null
    }

    override suspend fun ImageJsonToBitmap(imageJson: JSONObject): Bitmap? {
        val imageRegularUrl = imageJson.getJSONObject("urls").getString("regular")
        Log.d(TAG, imageRegularUrl.toString())
        try {
            val inStream = withContext(Dispatchers.IO) {
                URL(imageRegularUrl).openStream()
            }
            return BitmapFactory.decodeStream(inStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}