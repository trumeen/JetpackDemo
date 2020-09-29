package io.github.trumeen.ui.eyepetizer.lighttopic

import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.EyepetizerDataSource
import io.github.trumeen.net.VideoApi


class LightTopicDataSource(
    private var api: VideoApi,
    private var id: Int,
    private val viewModel: LightTopicViewModel
) :
    EyepetizerDataSource<Int, RecommendItemBean>() {


    override suspend fun loadData(params: LoadParams<Int>): LoadResult<Int, RecommendItemBean> {
        val response = api.getTopicResult(id)
        viewModel.currentData.postValue(response)
        return LoadResult.Page(
            data = response.itemList,
            prevKey = null,
            nextKey = null
        )
    }
}