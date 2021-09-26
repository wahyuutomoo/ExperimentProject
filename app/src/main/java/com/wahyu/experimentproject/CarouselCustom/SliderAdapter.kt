package com.wahyu.experimentproject.CarouselCustom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.wahyu.experimentproject.databinding.SliderItemBinding


class SliderAdapter(
    val sliderItems: MutableList<SliderItem>,
    val viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(val binding: SliderItemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SliderViewHolder (
        SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val data = sliderItems[position]

        Glide
            .with(holder.itemView.context)
            .load(data.image)
            .into(holder.binding.ivImage)

//        if(position == sliderItems.size - 2){ // untuk unlimited scroll ke kanan image
//            viewPager2.post(runnable)
//        }

    }

    override fun getItemCount() = sliderItems.size

    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

}