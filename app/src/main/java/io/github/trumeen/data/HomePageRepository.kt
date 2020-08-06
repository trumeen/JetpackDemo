package io.github.trumeen.data

import androidx.paging.*
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_RECOMMEND_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class HomePageRepository(
    viewModelScope: CoroutineScope
) {

    private var mPager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {
            HomePageDataSource(
                VideoApi.get(EYEPETTIZER_BASE_URL),
                EYEPETTIZER_RECOMMEND_URL
            )
        }
    ).flow.cachedIn(viewModelScope)

    fun getPageData(): Flow<PagingData<RecommendItemBean>> {
        return mPager

    }


}