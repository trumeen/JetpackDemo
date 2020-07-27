package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.io.IOException

class CalendarPageDataSource(val api: VideoApi) : PagingSource<String, RecommendItemBean>() {

    private var lastUrl: String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RecommendItemBean> {
        val url: String = params.key ?: ""
        val response = if (!url.isNullOrEmpty()) {
            lastUrl = url
            api.getCalendarNextData(url)
        } else {
            lastUrl = null
            api.getCalendarData()
        }
        return try {
            LoadResult.Page(
                data = response.itemList,
                prevKey = if (lastUrl.isNullOrEmpty()) null else lastUrl,
                nextKey = if (response.nextPageUrl.isNullOrEmpty()) null else response.nextPageUrl
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}