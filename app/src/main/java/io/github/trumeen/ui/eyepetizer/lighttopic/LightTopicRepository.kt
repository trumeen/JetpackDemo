package io.github.trumeen.ui.eyepetizer.lighttopic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LightTopicRepository(
    val viewModel: LightTopicViewModel,
    val viewModelScope: CoroutineScope
) {

    fun getTopicResult(id: Int): Flow<PagingData<RecommendItemBean>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                LightTopicDataSource(
                    VideoApi.get(EYEPETTIZER_BASE_URL),
                    id,
                    viewModel
                )
            }
        ).flow.cachedIn(viewModelScope)
    }


}
