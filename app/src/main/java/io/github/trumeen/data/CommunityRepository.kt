package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.Tab
import io.github.trumeen.bean.TabInfo
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class CommunityRepository(val api: VideoApi) {

    fun getCommunityData(url:String): Flow<PagingData<RecommendItemBean>> {
        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CommunityDateSource(api,url) }).flow
    }

    suspend fun getCommunityTabs():List<Tab>{
        return api.getCommunityTabs().tabInfo.tabList
    }

}