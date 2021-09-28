package com.wahyu.experimentproject.VideoScroll

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.wahyu.experimentproject.databinding.ItemVideoScrollBinding
import com.google.android.exoplayer2.util.Util


class VideoScrollAdapter(
    val videoItem: ArrayList<VideoItem>,
    val context: Context,
    val recyclerView: RecyclerView
): RecyclerView.Adapter<VideoScrollAdapter.ViewHolder>() {

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VideoScrollAdapter.ViewHolder (
        ItemVideoScrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VideoScrollAdapter.ViewHolder, position: Int) {
      val data = videoItem[position]

        println("EDJDJKHKFJ ${holder.getAdapterPosition()}")
        initPlayer(holder)
        holder.binding.exoPlayerView.hideController()
//        simpleExoPlayer.play()
    }

    override fun getItemCount() = videoItem.size

    class ViewHolder(val binding: ItemVideoScrollBinding): RecyclerView.ViewHolder(binding.root)

    private fun initPlayer(holder: ViewHolder) {
        simpleExoPlayer = SimpleExoPlayer.Builder(context).build()
        playerListiner(holder)

        createMediaSource()
        holder.binding.exoPlayerView.player = simpleExoPlayer
        mediaSource?.let { simpleExoPlayer.setMediaSource(it) }
        simpleExoPlayer.prepare()
    }

    private fun createMediaSource(){
        simpleExoPlayer.seekTo(0)
        val dataSourceFactory:  DataSource.Factory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context, context.applicationInfo.name)
        )

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
            MediaItem.fromUri("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
        )
    }

    private fun playerListiner(holder: ViewHolder) {
         var playerListiner = object : Player.Listener{
            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()
//                holder.binding.exoPlayerView.useController = true
            }

             override fun onPlayerError(error: PlaybackException) {
                 super.onPlayerError(error)

                 println("ERRORVIDEO $error")
             }
        }

        simpleExoPlayer.addListener(playerListiner)
    }


}