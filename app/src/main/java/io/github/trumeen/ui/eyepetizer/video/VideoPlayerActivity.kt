package io.github.trumeen.ui.eyepetizer.video

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import coil.api.load
import com.google.android.exoplayer2.SeekParameters
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.VIDEO_INFO
import io.github.trumeen.bean.VideoInfoBean
import io.github.trumeen.databinding.ActivityVideoPlayerBinding
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager

class VideoPlayerActivity : BaseVmActivity<VideoPlayerViewModel>() {


    private var isPlay: Boolean = false
    private var isPause: Boolean = true
    private var orientationUtils: OrientationUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoInfo = intent.getParcelableExtra<VideoInfoBean>(VIDEO_INFO)
        val binding =
            DataBindingUtil.setContentView<ActivityVideoPlayerBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        mViewModel.videoInfo.value = videoInfo
        initVideo(videoInfo)
        resolveNormalVideoUI()
    }

    private fun initVideo(videoInfo: VideoInfoBean) {
        orientationUtils = OrientationUtils(this, video)
        //初始化不打开外部的旋转
        orientationUtils?.isEnable = false
        //增加封面
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.load(videoInfo.coverUrl)
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption.setThumbImageView(imageView)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(videoInfo.playUrl)
            .setVideoTitle(videoInfo.title)
            .setCacheWithPlay(false)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any?) {
                    super.onPrepared(url, *objects)
                    orientationUtils!!.isEnable = true
                    isPlay = true
                    if (video.gsyVideoManager.player is Exo2PlayerManager) {
                        (video.gsyVideoManager
                            .player as Exo2PlayerManager).setSeekParameter(SeekParameters.NEXT_SYNC)
                    }
                }

                override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                    super.onQuitFullscreen(url, *objects)
                    orientationUtils?.backToProtVideo()
                }
            })
            .setLockClickListener { _, lock ->
                //配合下方的onConfigurationChanged
                orientationUtils?.isEnable = !lock
            }.build(video)
        video.fullscreenButton
            .setOnClickListener(View.OnClickListener { //直接横屏
                orientationUtils!!.resolveByClick()
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                video.startWindowFullscreen(this, true, true)
            })

        video.setOnBackClickListener(View.OnClickListener { finish() })
    }

    private fun resolveNormalVideoUI() {
        video.hideTitle()
    }

    override fun onBackPressed() {
        orientationUtils?.backToProtVideo()
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    private fun getCurPlay(): GSYVideoPlayer {
        return video.fullWindowPlayer ?: video
    }

    override fun onPause() {
        getCurPlay().onVideoPause()
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        getCurPlay().onVideoResume(false)
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            getCurPlay().release()
        }
        orientationUtils?.releaseListener()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            video.onConfigurationChanged(this, newConfig, orientationUtils, true, true)
        }
    }


    override fun layoutRes() = R.layout.activity_video_player

    override fun viewModelClass(): Class<VideoPlayerViewModel> {
        return VideoPlayerViewModel::class.java
    }
}