package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.Tab
import io.github.trumeen.data.CommunityRepository
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.flow.Flow

class CommunityViewModel : BaseViewModel() {

    private var mRepository = CommunityRepository(VideoApi.get(EYEPETTIZER_BASE_URL))

    fun getCommunityData(url:String): Flow<PagingData<RecommendItemBean>> {
        return mRepository.getCommunityData(url)
    }


    suspend fun getTabs(): List<Tab> {
        return mRepository.getCommunityTabs()
    }

}