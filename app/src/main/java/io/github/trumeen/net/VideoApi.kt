package io.github.trumeen.net

import com.blankj.utilcode.util.PathUtils
import io.github.trumeen.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
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


}