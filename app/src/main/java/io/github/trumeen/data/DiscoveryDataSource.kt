package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.io.IOException

private const val FIRST_INDEX = 0

class DiscoveryDataSource(private var api: VideoApi, private var requestUrl: String) :
    EyepetizerDataSource<String, RecommendItemBean>() {

    private var lastUrl: String? = null

    override suspend fun loadData(params: LoadParams<String>): LoadResult<String, RecommendItemBean> {
        val url: String = params.key ?: ""
        val response = if (!url.isNullOrEmpty()) {
            lastUrl = url
            api.getRecommendNextData(url)
        } else {
            lastUrl = null
            api.getRecommendList(url = requestUrl, page = FIRST_INDEX)
        }
        return LoadResult.Page(
            data = response.itemList,
            prevKey = if (lastUrl.isNullOrEmpty()) null else lastUrl,
            nextKey = if (response.nextPageUrl.isNullOrEmpty()) null else response.nextPageUrl
        )
    }
}