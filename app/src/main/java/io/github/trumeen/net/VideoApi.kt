package io.github.trumeen.net

import com.blankj.utilcode.util.PathUtils
import io.github.trumeen.BuildConfig
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.bean.RecommendBean
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * @author  Administrator
 * @time    2019/10/5 14:57
 * @des        ${TODO}
 *
 * @version      $Rev$
 * @updateAuthor $Author$
 * @updateDate   $Date$
 * @updateDes    ${TODO}
 */
interface VideoApi {

    companion object {
        fun get(baseUrl: String): VideoApi {
            val SIZE_OF_CACHE = 100 * 1024 * 1024.toLong() // 10 MiB
            val cacheFile: String = PathUtils.getExternalAppCachePath()
            val file = File(cacheFile)
            if (!file.exists()) {
                file.mkdirs()
            }
            val cache = Cache(file, SIZE_OF_CACHE)
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR) //有网络时的拦截器
                .addInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)//没网络时的拦截器
                .cache(cache)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(Base64GsonConverterFacctory.create(Gson()))
                .build()
                .create(VideoApi::class.java)
        }


        fun getXml(): VideoApi {
            val SIZE_OF_CACHE = 100 * 1024 * 1024.toLong() // 10 MiB
            val cacheFile: String = PathUtils.getExternalAppCachePath()
            val file = File(cacheFile)
            if (!file.exists()) {
                file.mkdirs()
            }
            val cache = Cache(file, SIZE_OF_CACHE)
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR) //有网络时的拦截器
                .addInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)//没网络时的拦截器
                .cache(cache)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            return Retrofit.Builder()
                .client(clientBuilder.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                //.addConverterFactory(Base64GsonConverterFacctory.create(Gson()))
                .baseUrl("http://sscj8.com/")
                .build()
                .create(VideoApi::class.java)
        }
    }

    @GET("")
    suspend fun getRecommendList(
        @Url url: String, @Query("page") page: Int = 0,
        @Query("isOldUser") isOldUser: Boolean = true,
        @Query("udid") uuid: String = "74b07e6bb02b436b92387a6a8a37d7f4414c1434",
        @Query("vc") vc: Int = 6030015,
        @Query("vn") vn: String = "6.2.1",
        @Query("size") size: String = "1080X2208",
        @Query("deviceModel") deviceModel: String = "PCT-AL10",
        @Query("first_channel") first_channel: String = "eyepetizer_yingyongbao_market",
        @Query("last_channel") last_channel: String = "eyepetizer_yingyongbao_market",
        @Query("system_version_code") system_version_code: Int = 29
    ): RecommendBean


}