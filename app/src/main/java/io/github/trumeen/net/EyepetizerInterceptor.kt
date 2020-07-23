package io.github.trumeen.net

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlin.random.Random


class EyepetizerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
//添加请求参数，此处是以豆瓣api为例，下面会贴出Base_url
        //添加请求参数，此处是以豆瓣api为例，下面会贴出Base_url
        val url: HttpUrl = original.url.newBuilder()
            .addQueryParameter("udid", "74b07e6bb02b436b92387a6a8a37d7f4414c1434")
            .addQueryParameter("vc", Random.nextInt(1, 1000000).toString())
            .addQueryParameter("vn", "6.2.1")
            .addQueryParameter("size", "1080X2208")
            .addQueryParameter("deviceModel", "PCT-AL10")
            .addQueryParameter("first_channel", "eyepetizer_yingyongbao_market")
            .addQueryParameter("last_channel", "eyepetizer_yingyongbao_market")
            .addQueryParameter("system_version_code", "29")
            .addQueryParameter("isOldUser", "true")
            .build()
//添加请求头
        //添加请求头
        val request: Request = original.newBuilder()
/*            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Connection", "keep-alive")*/
            .method(original.method, original.body)
            .url(url)
            .build()
        return chain.proceed(request)

    }
}