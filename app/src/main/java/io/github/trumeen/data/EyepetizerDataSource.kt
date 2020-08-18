package io.github.trumeen.data

import androidx.paging.PagingSource
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import io.github.trumeen.App
import io.github.trumeen.R
import io.github.trumeen.extension.showToast
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class EyepetizerDataSource<S : Any, B : Any> : PagingSource<S, B>() {

    override suspend fun load(params: LoadParams<S>): LoadResult<S, B> {
        return try {
            loadData(params)
        } catch (e: Exception) {
            when (e) {
                is ConnectException -> {
                    // 连接失败
                    App.instance.showToast(App.instance.getString(R.string.network_connection_failed))
                }
                is SocketTimeoutException -> {
                    // 请求超时
                    App.instance.showToast(App.instance.getString(R.string.network_request_timeout))
                }
                is JsonParseException -> {
                    // 数据解析错误
                    App.instance.showToast(App.instance.getString(R.string.api_data_parse_error))
                }
                is IOException -> {
                    App.instance.showToast(App.instance.getString(R.string.api_net_error_tips))
                }
            }
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    abstract suspend fun loadData(params: LoadParams<S>): LoadResult<S, B>
}