package com.wahyu.experimentproject.VideoScroll

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.*
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.Util
import com.wahyu.experimentproject.CarouselCustom.SliderItem
import com.wahyu.experimentproject.databinding.ActivityCarouselBinding
import com.wahyu.experimentproject.databinding.ActivityCarouselBinding.inflate
import com.wahyu.experimentproject.databinding.ActivityVideoScrollBinding
import com.wahyu.experimentproject.databinding.ActivityVideoScrollBinding.inflate

class VideoScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoScrollBinding
    private lateinit var videoScrollAdapter: VideoScrollAdapter
    val videoItem: ArrayList<VideoItem> = ArrayList()
    private val snapHelper = PagerSnapHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVideoScrollBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        handleAdapter()
    }


    private fun initData() {
        videoItem.add(VideoItem("Wahyu"))
        videoItem.add(VideoItem("Hafzan"))
        videoItem.add(VideoItem("Tanpa Nama"))
        videoItem.add(VideoItem("Jangkrik"))
    }


    private fun handleAdapter() {
        videoScrollAdapter = VideoScrollAdapter(videoItem, this, binding.rvVideo)
        binding.rvVideo.apply {
            adapter = videoScrollAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        }
        snapHelper.attachToRecyclerView(binding.rvVideo)



        val snapOnScrollListener = SnapOnScrollListene(snapHelper)
        binding.rvVideo.addOnScrollListener(snapOnScrollListener)



        println("snapOnScrollListener $snapOnScrollListener")

      }

}