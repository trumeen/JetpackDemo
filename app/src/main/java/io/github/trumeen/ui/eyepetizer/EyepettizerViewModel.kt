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

    var mNotificationPageIndex: Int = 0
    var mCommunityPageIndex: Int = 0
    var mHomeFragmentIndex: Int = 1
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

    //推荐数据
    private var mRecommendResult = MutableLiveData<PagingData<RecommendItemBean>>()

    //社区页面TAB
    var mCommunityTabs = MutableLiveData<List<Tab>>()

    //通知页面TAB
    var mMessagesTabs = MutableLiveData<List<Tab>>()

    init {
        itemDataSet.value = ObservableArrayList()
        mRecommendResult.value = PagingData.empty()
    }

    //首页TAB index

    /**
     * 获取发现数据列表
     */
    fun getDiscoveryData(): Flow<PagingData<UiModel>> {
        return discoveryRepository.getPageData().map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
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

    /**
     * 获取首页推荐数据
     */
    fun getRecommendPagingData(): Flow<PagingData<UiModel>> {
        return recommendRepository.getPageData().map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
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


    /**
     * 获取日报列表数据
     */
    fun getDailyPagingData(): Flow<PagingData<UiModel>> {
        return dailyRepository.getPageData().map { pagingData ->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
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

    /**
     * 获取社区列表
     */
    fun getCommunityData(url: String): Flow<PagingData<UiModel>> {
        return mCommunityRepository.getCommunityData(url).map { pagingData ->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
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

    /**
     * 获取社区推荐内容列表
     */
    fun getCommunityRecData(url: String): Flow<PagingData<UiModel>> {
        return mCommunityRepository.getCommunityRecData(url).map { pagingData ->
            pagingData.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
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


    fun getCommunityTabs() {
        launch(block = {
            mCommunityTabs.value = mCommunityRepository.getCommunityTabs()
        })

//        return mCommunityRepository.getCommunityTabs()
    }

    /**
     * 获取消息Tab列表
     */
    fun getMessagesTabs() {
        launch(block = {
            mMessagesTabs.value = mMessageRepository.getMessageTabs()
        })
    }


    /**
     * 获取推送的消息列表
     */
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