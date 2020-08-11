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
    private val discoveryRepository = DiscoveryRepository(viewModelScope)
    var mCommunityRepository = CommunityRepository(viewModelScope)
    private val mMessageRepository = MessageRepository(viewModelScope)
    private var mRecommendResult = MutableLiveData<PagingData<RecommendItemBean>>()
    var mCommunityTabs = MutableLiveData<List<Tab>>()
    var mMessagesTabs = MutableLiveData<List<Tab>>()

    init {
        itemDataSet.value = ObservableArrayList()
        mRecommendResult.value = PagingData.empty()
    }

    fun getDiscoveryData(): Flow<PagingData<UiModel>> {
        return discoveryRepository.getPageData().map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem,UiModel>{ before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }

    }


    fun getRecommendPagingData(): Flow<PagingData<UiModel>> {
        return recommendRepository.getPageData().map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem,UiModel>{ before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }


    fun getDailyPagingData(): Flow<PagingData<UiModel>> {
        return dailyRepository.getPageData().map { pagingData->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem,UiModel>{ before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }

    fun getCommunityData(url: String): Flow<PagingData<UiModel>> {
        return mCommunityRepository.getCommunityData(url).map { pagingData->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem,UiModel>{ before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }

    fun getCommunityRecData(url: String): Flow<PagingData<UiModel>> {
        return mCommunityRepository.getCommunityRecData(url).map { pagingData->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem,UiModel>{ before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }


    suspend fun getCommunityTabs() {
        mCommunityTabs.value = mCommunityRepository.getCommunityTabs()
//        return mCommunityRepository.getCommunityTabs()
    }

    suspend fun getMessagesTabs() {
        mMessagesTabs.value = mMessageRepository.getMessageTabs()
//        return mCommunityRepository.getCommunityTabs()
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