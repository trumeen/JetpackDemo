package io.github.trumeen.ui.eyepetizer

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import androidx.paging.*
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.Tab
import io.github.trumeen.data.*
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.notifications.toWeekString
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val api = VideoApi.get(EYEPETTIZER_BASE_URL)

class EyepettizerViewModel : BaseViewModel() {

    val mBottomButtonState: MutableLiveData<Boolean> = MutableLiveData(true)
    val itemDataSet = MutableLiveData<ObservableArrayList<RecommendItemBean>>()
    private val recommendRepository =
        HomePageRepository(
            viewModelScope
        )
    private val dailyRepository = DailyPageRepository(viewModelScope)
    var mCommunityRepository = CommunityRepository(viewModelScope)
    private val mMessageRepository = MessageRepository(viewModelScope)
    private var mRecommendResult = MutableLiveData<PagingData<RecommendItemBean>>()

    init {
        itemDataSet.value = ObservableArrayList()
        mRecommendResult.value = PagingData.empty()
    }

    fun getDiscoveryData() {
        launch(block = {
            val recommendList = VideoApi.get("http://baobab.kaiyanapp.com/api/v5/")
                .getRecommendList("http://baobab.kaiyanapp.com/api/v7/index/tab/discovery")
            itemDataSet.value?.addAll(recommendList.itemList)
        }, error = {
            println("异常:${it.message}")
        })

    }


    fun getRecommendPagingData(): Flow<PagingData<RecommendItemBean>> {
        return recommendRepository.getPageData()
    }


    fun getDailyPagingData(): Flow<PagingData<RecommendItemBean>> {
        return dailyRepository.getPageData()
    }

    fun getCommunityData(url: String): Flow<PagingData<RecommendItemBean>> {
        return mCommunityRepository.getCommunityData(url)
    }


    suspend fun getTabs(): List<Tab> {
        return mCommunityRepository.getCommunityTabs()
    }


    fun getMessageList(): Flow<PagingData<NotificationUiModel>> {
        return mMessageRepository.getMessageList().map { pagingDate ->
            pagingDate.map {
                NotificationUiModel.NotificationContent(
                    it.actionUrl,
                    it.content,
                    it.date.toWeekString(),
                    it.icon,
                    it.id,
                    it.ifPush,
                    it.pushStatus,
                    it.title,
                    it.viewed
                )
            }
        }.map {
            it.insertSeparators<NotificationUiModel.NotificationContent, NotificationUiModel> { before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators NotificationUiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }
}