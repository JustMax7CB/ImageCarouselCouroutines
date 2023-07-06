package com.example.imagegallerycoroutines.view.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallerycoroutines.R
import com.example.imagegallerycoroutines.data.repository.ImageApiImpl
import com.example.imagegallerycoroutines.model.ItemModelImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarouselRVAdapter :
    RecyclerView.Adapter<CarouselRVAdapter.CarouselItemViewHolder>() {

    private var data: ArrayList<Bitmap> = arrayListOf()

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {

        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.image_view)
        imageView.setImageBitmap(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun addItem(image: Bitmap) {
        data.add(image)
        notifyDataSetChanged()
    }

}