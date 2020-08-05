package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.Message
import io.github.trumeen.net.VideoApi
import java.io.IOException

class MessageDateSource(val api: VideoApi) : PagingSource<String, Message>() {
    private var lastUrl: String? = null


    override suspend fun load(params: LoadParams<String>): LoadResult<String, Message> {
        val url: String = params.key ?: ""
        val response = if (!url.isNullOrEmpty()) {
            lastUrl = url
            api.getMessageNextData(url)
        } else {
            lastUrl = null
            api.getMessageData()
        }
        return try {
            LoadResult.Page(
                data = response.messageList,
                prevKey = if (lastUrl.isNullOrEmpty()) null else lastUrl,
                nextKey = if (response.nextPageUrl.isNullOrEmpty()) null else response.nextPageUrl
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }


}