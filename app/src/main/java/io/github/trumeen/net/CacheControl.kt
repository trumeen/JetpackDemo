package io.github.trumeen.net

import com.blankj.utilcode.util.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * @author  Administrator
 * @time    2020/4/19 20:04
 * @des
 *
 * @version      $
 * @updateAuthor $
 * @updateDate   $
 * @updateDes
 */
object CacheControl {
    val TIMEOUT_CONNECT = 60 * 60 * 24 * 1//1天
    val TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7 //7天
    val REWRITE_RESPONSE_INTERCEPTOR: Interceptor
        get() = Interceptor { chain ->
            //获取retrofit @headers里面的参数，参数可以自己定义，在本例我自己定义的是cache，跟@headers里面对应就可以了
            var cache = chain.request().header("cache")
            var originalResponse: Response = chain.proceed(chain.request())
            val cacheControl = originalResponse.header("Cache-Control")
            if (cacheControl == null) { //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "" == cache) {
                    cache = TIMEOUT_CONNECT.toString() + ""
                }
                originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=$cache")
                    .build()
                originalResponse
            } else {
                originalResponse
            }
        }


    val REWRITE_RESPONSE_INTERCEPTOR_OFFLINE: Interceptor
        get() = Interceptor { chain ->

            var request: Request = chain.request()
            //离线的时候为7天的缓存。
            if (!NetworkUtils.isAvailable()) {
                request = request.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$TIMEOUT_DISCONNECT"
                    )
                    .build()
            }
            chain.proceed(request)
        }
}