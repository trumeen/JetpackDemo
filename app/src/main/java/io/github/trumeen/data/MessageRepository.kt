package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.trumeen.bean.Message
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.flow.Flow

class MessageRepository(val api: VideoApi) {

    fun getMessageList(): Flow<PagingData<Message>> {
        return Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { MessageDateSource(api) }).flow
    }


}