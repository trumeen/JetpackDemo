package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.io.IOException
import java.util.*

private val CURRENT_TIME = Calendar.getInstance().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}.timeInMillis

class DailyPageDataSource(private var api: VideoApi, private var url: String) :
    PagingSource<Long, RecommendItemBean>() {
    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, RecommendItemBean> {
        val position: Long = params.key ?: CURRENT_TIME
        val timeInMillis = CURRENT_TIME
        println("time--->$timeInMillis position:$position")
        val response = api.getDailyList(url = url, date = position)
        return try {
            LoadResult.Page(
                data = response.itemList,
                prevKey = if (position == CURRENT_TIME) null else Calendar.getInstance().apply {
                    setTimeInMillis(position)
                    add(Calendar.DATE, +1)
                }.timeInMillis,
                nextKey = if (response.itemList.isNullOrEmpty()) null else Calendar.getInstance()
                    .apply {
                        setTimeInMillis(position)
                        add(Calendar.DATE, -1)
                    }.timeInMillis
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}