package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.CalendarPageDataSource
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow
import java.util.*

class CalendarRepository(val api: VideoApi) {

    private lateinit var mCalendarPageDataSource: CalendarPageDataSource

    suspend fun getCalendarData(date: String): List<RecommendItemBean> {
        return api.getCalendarData(date).itemList
    }

    fun getPageData(date: Date): Flow<PagingData<RecommendItemBean>> {
        println("CalendarRepository--->getPageData:${date.time}")
//        mCalendarPageDataSource = CalendarPageDataSource(api, date)
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CalendarPageDataSource(api, date) }
        ).flow

    }

    fun refreshDate(date: Date){
        CalendarPageDataSource.setRefreshKey(date)
    }
}