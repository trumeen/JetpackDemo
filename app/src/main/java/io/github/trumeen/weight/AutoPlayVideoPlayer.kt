package io.github.trumeen.weight

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView
import io.github.trumeen.R

class AutoPlayVideoPlayer: StandardGSYVideoPlayer {

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    constructor(context: Context?, fullFlag: Boolean?) : super(
        context,
        fullFlag
    ) {
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    override fun init(context: Context) {
        super.init(context)
        post {
            gestureDetector = GestureDetector(
                getContext().applicationContext,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onDoubleTap(e: MotionEvent): Boolean {
                        touchDoubleUp()
                        return super.onDoubleTap(e)
                    }

                    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                        if (!mChangePosition && !mChangeVolume && !mBrightness) {
                            onClickUiToggle()
                        }
                        return super.onSingleTapConfirmed(e)
                    }

                    override fun onLongPress(e: MotionEvent) {
                        super.onLongPress(e)
                    }
                })
        }
    }

    //这个必须配置最上面的构造才能生效
    override fun getLayoutId(): Int {
        return  R.layout.auto_play_video_layout
    }



    override fun updateStartImage() {
        if (mIfCurrentIsFullscreen) {
            if (mStartButton is ImageView) {
                val imageView = mStartButton as ImageView
                if (mCurrentState == GSYVideoView.CURRENT_STATE_PLAYING) {
                    imageView.setImageResource(R.drawable.video_click_pause_selector)
                } else if (mCurrentState == GSYVideoView.CURRENT_STATE_ERROR) {
                    imageView.setImageResource(R.drawable.video_click_play_selector)
                } else {
                    imageView.setImageResource(R.drawable.video_click_play_selector)
                }
            }
        } else {
            super.updateStartImage()
        }
    }


    override fun getEnlargeImageRes(): Int {
        return R.drawable.ic_action_full_screen
    }

    override fun getShrinkImageRes(): Int {
        return R.drawable.ic_action_share
    }



}