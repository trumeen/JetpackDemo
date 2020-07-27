package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.CalendarPageDataSource
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow

class CalendarRepository(val api: VideoApi) {

    suspend fun getCalendarData(date: String): List<RecommendItemBean> {
        return api.getCalendarData(date).itemList
    }

    fun getPageData(): Flow<PagingData<RecommendItemBean>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CalendarPageDataSource(api) }
        ).flow

    }
}