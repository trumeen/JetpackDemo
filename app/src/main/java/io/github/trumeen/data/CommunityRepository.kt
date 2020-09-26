package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.Tab
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class CommunityRepository(
    val viewModelScope: CoroutineScope,
    var viewModel: EyepettizerViewModel
) {


    private var map = HashMap<String, Flow<PagingData<RecommendItemBean>>>()


    fun getCommunityData(url: String): Flow<PagingData<RecommendItemBean>> {
        if (map[url] == null) {
            map[url] = Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = {
                    CommunityDateSource(
                        VideoApi.get(EYEPETTIZER_BASE_URL),
                        url,
                        viewModel
                    )
                }).flow.cachedIn(viewModelScope)

        }
        return map[url]!!
    }

    fun getCommunityRecData(url: String): Flow<PagingData<RecommendItemBean>> {
        if (map[url] == null) {
            map[url] = Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = {
                    CommunityDateSource(
                        VideoApi.get(EYEPETTIZER_BASE_URL),
                        url,
                        viewModel
                    )
                }).flow.cachedIn(viewModelScope)

        }
        return map[url]!!
    }

    suspend fun getCommunityTabs(): List<Tab> {
        return VideoApi.get(EYEPETTIZER_BASE_URL).getCommunityTabs().tabInfo.tabList
    }

}