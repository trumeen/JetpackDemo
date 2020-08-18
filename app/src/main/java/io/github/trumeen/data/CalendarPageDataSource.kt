package io.github.trumeen.data

import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.text.SimpleDateFormat
import java.util.*

class CalendarPageDataSource(val api: VideoApi, var date: Date) :
    EyepetizerDataSource<String, RecommendItemBean>() {


    private var lastUrl: String? = null

    companion object {
        private var mRefreshDate: Date? = null
        fun setRefreshKey(date: Date) {
            mRefreshDate = date
        }
    }

    override suspend fun loadData(params: LoadParams<String>): LoadResult<String, RecommendItemBean> {
        val url: String = params.key ?: ""
        val response = if (!url.isNullOrEmpty()) {
            lastUrl = url
            api.getCalendarNextData(url)
        } else {
            lastUrl = null
            api.getCalendarData(SimpleDateFormat("yyyy-MM-dd").format(mRefreshDate ?: date))
        }
        return LoadResult.Page(
            data = response.itemList,
            prevKey = if (lastUrl.isNullOrEmpty()) null else lastUrl,
            nextKey = if (response.nextPageUrl.isNullOrEmpty()) null else response.nextPageUrl
        )
    }


}