package io.github.trumeen.ui.eyepetizer.video

import android.os.Bundle
import io.github.trumeen.R
import io.github.trumeen.bean.VIDEO_ID
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : BaseVmActivity<VideoPlayerViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoId = intent.getIntExtra(VIDEO_ID, 0)
        textView.text = videoId.toString()
        println("videoId: $videoId")
    }



    override fun layoutRes()= R.layout.activity_video_player

    override fun viewModelClass(): Class<VideoPlayerViewModel> {
        return VideoPlayerViewModel::class.java
    }
}