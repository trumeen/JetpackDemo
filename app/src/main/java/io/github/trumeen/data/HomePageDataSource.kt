package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.io.IOException

private const val FIRST_INDEX = 0

class HomePageDataSource(private var api: VideoApi, private var url: String) :
    PagingSource<Int, RecommendItemBean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendItemBean> {
        val position = params.key ?: FIRST_INDEX
        val response = api.getRecommendList(url= url, page = position)
        return try {
            LoadResult.Page(
                data = response.itemList,
                prevKey = if (position == FIRST_INDEX) null else position - 1,
                nextKey = if (response.itemList.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}