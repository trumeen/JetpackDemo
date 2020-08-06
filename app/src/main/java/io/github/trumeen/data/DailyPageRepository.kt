package io.github.trumeen.data

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_DAILY_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class DailyPageRepository(private val viewModelScope: CoroutineScope) {

    var pager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {
            DailyPageDataSource(
                VideoApi.get(EYEPETTIZER_BASE_URL),
                EYEPETTIZER_DAILY_URL
            )
        }
    ).flow.cachedIn(viewModelScope)

    fun getPageData(): Flow<PagingData<RecommendItemBean>> {
        return pager

    }
}