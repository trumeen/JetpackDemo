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


class EyepetizerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val url: HttpUrl = original.url.newBuilder()
            .addQueryParameter("udid", "74b07e6bb02b436b92387a6a8a37d7f4414c1434")
            .addQueryParameter("vc", App.versionCode.toString())
            .addQueryParameter("vn", App.versionName)
            .addQueryParameter(
                "size",
                "${ScreenUtils.getAppScreenWidth()}X${ScreenUtils.getScreenHeight()}"
            )
            .addQueryParameter("deviceModel", DeviceUtils.getModel())
            .addQueryParameter("first_channel", "eyepetizer_yingyongbao_market")
            .addQueryParameter("last_channel", "eyepetizer_yingyongbao_market")
            .addQueryParameter("system_version_code", DeviceUtils.getSDKVersionCode().toString())
            .addQueryParameter("isOldUser", "true")
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