package io.github.trumeen.ui.eyepetizer.video

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.*
import io.github.trumeen.databinding.ActivityVideoPlayerBinding
import io.github.trumeen.ui.base.BaseVmActivity

class VideoPlayerActivity : BaseVmActivity<VideoPlayerViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoInfo = intent.getParcelableExtra<VideoInfoBean>(VIDEO_INFO)
        val binding =
            DataBindingUtil.setContentView<ActivityVideoPlayerBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        mViewModel.videoInfo.value = videoInfo

    }


    override fun layoutRes() = R.layout.activity_video_player

    override fun viewModelClass(): Class<VideoPlayerViewModel> {
        return VideoPlayerViewModel::class.java
    }
}