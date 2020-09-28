package io.github.trumeen.ui.eyepetizer.ugc

import io.github.trumeen.bean.DataX
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL

/**
 * Created by Trumeen on 2020/9/27 09:25.
 */
class UgcPictureRepository {
    suspend fun getVideoReplies(videoId: Int, type: String): DataX {
        return VideoApi.get(EYEPETTIZER_BASE_URL).getUgcResult(videoId, type)
    }
}