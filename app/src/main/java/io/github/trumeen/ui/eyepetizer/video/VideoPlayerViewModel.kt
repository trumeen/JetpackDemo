package io.github.trumeen.ui.eyepetizer.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.trumeen.bean.Content
import io.github.trumeen.bean.DataX
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.VideoInfoBean
import io.github.trumeen.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoPlayerViewModel : BaseViewModel() {

    var repository = VideoInfoRepository()

    var mVideoRepliesInfo = MutableLiveData<ArrayList<RecommendItemBean>>()

    var videoInfo = MutableLiveData<VideoInfoBean>()


    suspend fun getVideoRepliesInfo(videoId: Int): Flow<LiveData<ArrayList<RecommendItemBean>>> {
        return repository.getVideoReplies(videoId).map {
            if (mVideoRepliesInfo.value == null) {
                mVideoRepliesInfo.value = ArrayList()
            }
            mVideoRepliesInfo.apply {
                value?.add(it)
            }
        }
    }

}