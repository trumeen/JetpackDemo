package io.github.trumeen.data

import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import java.io.IOException
import java.lang.Exception

class CommunityDateSource(val api: VideoApi, var getUrl: String) :
    EyepetizerDataSource<String, RecommendItemBean>() {

    private var lastUrl: String? = null

    override suspend fun loadData(params: LoadParams<String>): LoadResult<String, RecommendItemBean> {
        val url: String = params.key ?: ""
        val response = if (!url.isNullOrEmpty()) {
            lastUrl = url
            api.getCommunityNextData(url)
        } else {
            lastUrl = null
            api.getCommunityData(getUrl)
        }
        return LoadResult.Page(
            data = response.itemList,
            prevKey = if (lastUrl.isNullOrEmpty()) null else lastUrl,
            nextKey = if (response.nextPageUrl.isNullOrEmpty()) null else response.nextPageUrl
        )
    }
}