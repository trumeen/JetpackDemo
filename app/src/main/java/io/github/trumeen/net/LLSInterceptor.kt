package io.github.trumeen.net

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.ScreenUtils
import io.github.trumeen.App
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlin.random.Random


class LLSInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val url: HttpUrl = original.url.newBuilder()
            .addQueryParameter("appVersion", "8.7.0")
            .addQueryParameter("appId", "darwin")
            .addQueryParameter("deviceId", "e55a21cd39f4f6f3")
            .addQueryParameter("sDeviceId", "e55a21cd39f4f6f3")
            .build()
        val request: Request = original.newBuilder()
/*            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Connection", "keep-alive")*/
            .method(original.method, original.body)
            .url(url)
            .build()
        return chain.proceed(request)

    }
}