package com.wahyu.experimentproject.CarouselCustom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.wahyu.experimentproject.VideoScroll.VideoScrollActivity
import com.wahyu.experimentproject.databinding.ActivityCarouselBinding
import java.lang.Math.abs

class CarouselActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarouselBinding
    private val sliderHandler = Handler()
    val sliderItem: MutableList<SliderItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarouselBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sliderItem.add(SliderItem("https://media.suara.com/pictures/653x366/2020/07/06/71947-gambar-pemandangan.jpg"))
        sliderItem.add(SliderItem("https://dosenpintar.com/wp-content/uploads/2019/07/Screenshot_1-630x380-1.jpg"))
        sliderItem.add(SliderItem("https://wallpaperaccess.com/full/2802365.jpg"))
        sliderItem.add(SliderItem("https://i2.wp.com/blog.tripcetera.com/id/wp-content/uploads/2020/10/Danau-Toba-edited.jpg"))

        binding.vpImageSlider.adapter = SliderAdapter(sliderItem, binding.vpImageSlider)
        binding.vpImageSlider.clipToPadding = false
        binding.vpImageSlider.clipChildren = false
        binding.vpImageSlider.offscreenPageLimit = 3
        binding.vpImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

//        Tambahkan padding start 50 dan end 50 jika mau pakai ini
//        binding.vpImageSlider.setPageTransformer(compositePageTransformer)

        binding.vpImageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)

                if(position == sliderItem.size - 1){
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.vpImageSlider.setCurrentItem(0,true)
                    }, 3000)
                }

            }


        })

        binding.wormDotsIndicator.setViewPager2(binding.vpImageSlider)


        handleAction()
    }

    private val sliderRunnable = Runnable {
        binding.vpImageSlider.currentItem = binding.vpImageSlider.currentItem + 1
    }


    private fun handleAction() {
        binding.btnVideo.setOnClickListener {
            startActivity(Intent(this, VideoScrollActivity::class.java))
        }
    }



}