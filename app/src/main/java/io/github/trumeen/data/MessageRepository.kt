package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.trumeen.bean.Message
import io.github.trumeen.bean.Tab
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val viewModelScope: CoroutineScope) {

    private val pager = Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { MessageDateSource(VideoApi.get(EYEPETTIZER_BASE_URL)) }).flow.cachedIn(
        viewModelScope
    )

    fun getMessageList(): Flow<PagingData<Message>> {
        return pager
    }

    suspend fun getMessageTabs(): List<Tab>? {
        return VideoApi.get(EYEPETTIZER_BASE_URL).getMessageTabs().tabInfo.tabList
    }


}