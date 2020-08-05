package io.github.trumeen.ui.eyepetizer.fragment.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import io.github.trumeen.bean.Message
import io.github.trumeen.data.MessageRepository
import io.github.trumeen.data.NotificationUiModel
import io.github.trumeen.data.UiModel
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat

class NotificationsViewModel : BaseViewModel() {

    private val mRepository = MessageRepository(VideoApi.get(EYEPETTIZER_BASE_URL))

    fun getMessageList(): Flow<PagingData<NotificationUiModel>> {
        return mRepository.getMessageList().map { pagingDate ->
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

fun Long.toWeekString(): String {

    return when {
        System.currentTimeMillis() - this >= 7 * 24 * 60 * 60 * 1000 -> "一周前"
        System.currentTimeMillis() - this >= 6 * 24 * 60 * 60 * 1000 -> "6天前"
        System.currentTimeMillis() - this >= 5 * 24 * 60 * 60 * 1000 -> "5天前"
        System.currentTimeMillis() - this >= 4 * 24 * 60 * 60 * 1000 -> "4天前"
        System.currentTimeMillis() - this >= 3 * 24 * 60 * 60 * 1000 -> "3天前"
        System.currentTimeMillis() - this >= 2 * 24 * 60 * 60 * 1000 -> "2天前"
        System.currentTimeMillis() - this >= 1 * 24 * 60 * 60 * 1000 -> "1天前"
        else -> SimpleDateFormat("hh:mm").format(this)
    }


}