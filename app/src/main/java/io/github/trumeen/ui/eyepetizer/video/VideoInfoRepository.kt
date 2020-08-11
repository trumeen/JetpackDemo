package io.github.trumeen.ui.eyepetizer.video

import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

const val EYEPETTIZER_BASE_URL = "http://baobab.kaiyanapp.com/api/"
const val EYEPETTIZER_RECOMMEND_URL = "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec"
const val EYEPETTIZER_DISCOVERY_URL = "http://baobab.kaiyanapp.com/api/v7/index/tab/discovery"
const val EYEPETTIZER_DAILY_URL = "http://baobab.kaiyanapp.com/api/v5/index/tab/feed"

class VideoInfoRepository {

     suspend fun getVideoReplies(videoId: Int): Flow<RecommendItemBean> {
        return VideoApi.get(EYEPETTIZER_BASE_URL).getVideoReplies(videoId).itemList.asFlow()
    }
}