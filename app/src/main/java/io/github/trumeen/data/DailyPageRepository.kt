package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow

class DailyPageRepository(private val api: VideoApi) {

    fun getPageData(url: String): Flow<PagingData<RecommendItemBean>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { DailyPageDataSource(api, url) }
        ).flow

    }
}