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


class BmobInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val url: HttpUrl = original.url.newBuilder()
            .build()
        val request: Request = original.newBuilder()
            .addHeader("X-Bmob-Application-Id", "00eb3483ce9bafcf13186a01e5949f75")
            .addHeader("X-Bmob-REST-API-Key", "9806f1ba4e57a08ac9b21ec24544a25e")
            .method(original.method, original.body)
            .url(url)
            .build()
        return chain.proceed(request)

    }
}