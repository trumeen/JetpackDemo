package io.github.trumeen.net

import com.blankj.utilcode.util.PathUtils
import com.google.gson.JsonObject
import io.github.trumeen.BuildConfig
import io.github.trumeen.bean.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
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
                .connectTimeout(5, TimeUnit.SECONDS)
//                .addNetworkInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR) //有网络时的拦截器
                .addInterceptor(EyepetizerInterceptor())//没网络时的拦截器
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

        fun getLLS(baseUrl: String): VideoApi {
            val SIZE_OF_CACHE = 100 * 1024 * 1024.toLong() // 10 MiB
//            val cacheFile: String = PathUtils.getExternalAppCachePath()
//            val file = File(cacheFile)
//            if (!file.exists()) {
//                file.mkdirs()
//            }
//            val cache = Cache(file, SIZE_OF_CACHE)
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
//                .addNetworkInterceptor(CacheControl.REWRITE_RESPONSE_INTERCEPTOR) //有网络时的拦截器
                .addInterceptor(LLSInterceptor())//没网络时的拦截器
//                .cache(cache)
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

        fun getBmob(baseUrl: String): VideoApi {

            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(BmobInterceptor())//没网络时的拦截器
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VideoApi::class.java)
        }
    }

    @GET("")
    suspend fun getRecommendList(
        @Url url: String, @Query("page") page: Int = 0
    ): RecommendBean

    @GET("")
    suspend fun getRecommendNextData(
        @Url url: String
    ): RecommendBean

    @GET("")
    suspend fun getDailyList(
        @Url url: String, @Query("date") date: Long = Calendar.getInstance().timeInMillis
    ): RecommendBean

    @GET("v2/replies")
    suspend fun getVideoReplies(@Query("videoId") videoId: Int): RecommendBean


    @GET("v7/roamingCalendar/index")
    suspend fun getCalendarData(
        @Query("date") date: String = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().timeInMillis)
    ): RecommendBean


    @GET("")
    suspend fun getCalendarNextData(
        @Url url: String
    ): RecommendBean


    @GET("")
    suspend fun getCommunityNextData(@Url url: String): RecommendBean

    @GET("")
    suspend fun getCommunityData(
        @Url url: String,
        @Query("refreshCount") refreshCount: Int = 0
    ): RecommendBean

    @GET("v7/community/tab/list")
    suspend fun getCommunityTabs(): TabInfoBean

    @GET("v3/messages")
    suspend fun getMessageData(): MessageListBean

    @GET("")
    suspend fun getMessageNextData(@Url url: String): MessageListBean

    @GET("v3/messages/tabList")
    suspend fun getMessageTabs(): TabInfoBean

    @GET("lux_course/list")
    suspend fun getCurseList(
        @Query("tag") tag: Int = -1,
        @Query("token") token: String,
        @Query("page") page: Int,
        @Query("isEnd") isEnd: Boolean = true,
        @Query("pageNum") pageNum: Int = 20
    ): LLSCureseResultBean

    @GET("lux_klass_sessions/{id}/video")
    suspend fun getVideoInfo(
        @Path("id") id: String,
        @Query("token") token: String
    ): LLSVideoInfoBean

    @GET("elite_klass_sessions/{id}/content")
    suspend fun getContent(@Path("id") id: String, @Query("token") token: String): JsonObject

    @GET("course_list")
    suspend fun getBmobCurseList(
        @Query("skip") offset: Int,
        @Query("limit") limit: Int
    ): BmobCourseListBean


    @GET("course_content")
    suspend fun getBmobCurseContent(
        @Query("where") params: String
    ): CourseContentBean

}