package com.example.imagegallerycoroutines.view.main

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.imagegallerycoroutines.R
import com.example.imagegallerycoroutines.databinding.ActivityMainBinding
import com.example.imagegallerycoroutines.view.adapter.CarouselRVAdapter
import com.example.imagegallerycoroutines.viewmodel.main.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        applyViewPager()

        Log.d("MainActivity", mainViewModel.toString())


        mainViewModel.readImage.observe(this) { image ->
            Log.d("MainActivity", image.toString())
            (binding.viewPager.adapter as CarouselRVAdapter).addItem(image!!)
            binding.viewPager.setCurrentItem(binding.viewPager.adapter?.itemCount!!, true)
        }

        binding.buttonGet.setOnClickListener {
            mainViewModel.getRandomImage()

        }
    }

    private fun applyViewPager() {
        val compositePageTransformer = CompositePageTransformer()
        with(compositePageTransformer) {
            addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
            addTransformer { page, position ->
                val zoom = 1 - abs(position)
                page.scaleY = (0.80f + zoom * 0.20f)
            }
        }

        binding.viewPager.apply {
            adapter = CarouselRVAdapter()
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
        }


    }


}