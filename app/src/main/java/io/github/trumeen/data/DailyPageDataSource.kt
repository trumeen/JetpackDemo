package io.github.trumeen.data

import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.util.*

private val CURRENT_TIME = Calendar.getInstance().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}.timeInMillis

class DailyPageDataSource(private var api: VideoApi, private var url: String) :
    EyepetizerDataSource<Long, RecommendItemBean>() {

    override suspend fun loadData(params: LoadParams<Long>): LoadResult<Long, RecommendItemBean> {
        val position: Long = params.key ?: CURRENT_TIME
        val response = api.getDailyList(url = url, date = position)
        return LoadResult.Page(
            data = response.itemList,
            prevKey = if (position == CURRENT_TIME) null else Calendar.getInstance().apply {
                timeInMillis = position
                add(Calendar.DATE, +1)
            }.timeInMillis,
            nextKey = if (response.itemList.isNullOrEmpty()) null else Calendar.getInstance()
                .apply {
                    timeInMillis = position
                    add(Calendar.DATE, -1)
                }.timeInMillis
        )
    }
}