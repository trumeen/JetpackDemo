package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.gson.JsonObject
import io.github.trumeen.bean.BmobCourseContentBean
import io.github.trumeen.bean.Course
import io.github.trumeen.bean.CourseContentBean
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


class LLSCourseContentRepository(viewModelScope: CoroutineScope) {


    suspend fun getContentData(id: String): CourseContentBean {
        val params = JsonObject().apply {
            addProperty("id", id)
        }.toString()
        return VideoApi.getBmob(BMOB_BASE_URL).getBmobCurseContent(params)
    }

    fun getVideoData(id: String) {

    }


}
