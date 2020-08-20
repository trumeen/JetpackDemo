package io.github.trumeen.ui.lls

import android.net.Uri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hdl.m3u8.M3U8DownloadTask
import com.hdl.m3u8.bean.OnDownloadListener
import io.github.trumeen.R
import io.github.trumeen.bean.BmobCourseContentBean
import io.github.trumeen.data.LLS_BASE_URL
import io.github.trumeen.data.TOKEN
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_l_l_s_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class LLSMainActivity : BaseVmActivity<LLSMainViewModle>() {


    override fun viewModelClass(): Class<LLSMainViewModle> {
        return LLSMainViewModle::class.java
    }

    override fun layoutRes() = R.layout.activity_l_l_s_main

    override fun initData() {

        var courseListAdapter = CourseListAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@LLSMainActivity)
            adapter = courseListAdapter
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.getCurseList().collectLatest {
                courseListAdapter.submitData(it)
            }
        }

       /* GlobalScope.launch(Dispatchers.IO) {
            val api = VideoApi.getLLS(LLS_BASE_URL)


            (3..12).forEach {
                val curseList = api
                    .getCurseList(token = TOKEN, page = it)
                if (!curseList.courseList.isNullOrEmpty()) {
                    curseList.courseList.forEach { course ->
                        println("title-->${course.title}")
                        if (course.uri.isNullOrBlank()) {
                            val url = api.getVideoInfo(course.id, TOKEN)
                            withContext(Dispatchers.Main) {
                                val m3U8DownloadTask = M3U8DownloadTask(course.id)
                                m3U8DownloadTask.apply {
                                    saveFilePath = "/sdcard/LLS/${course.id}.ts"
                                    download(url.url, object : OnDownloadListener {
                                        override fun onSuccess() {
                                            println("${url} --下载成功")
                                        }

                                        override fun onDownloading(
                                            itemFileSize: Long,
                                            totalTs: Int,
                                            curTs: Int
                                        ) {
                                            println("${url} --下载中")
                                        }

                                        override fun onProgress(curLength: Long) {
                                            println("${url} --下载中--${curLength}")
                                        }

                                        override fun onError(errorMsg: Throwable?) {
                                            println("${url} --下载失败 ${errorMsg?.message}")
                                        }

                                        override fun onStart() {
                                            println("${url} --开始下载")
                                        }

                                    })
                                }
                                withContext(Dispatchers.IO) {
                                    while (m3U8DownloadTask.isRunning) {
                                        delay(1000)
                                    }
                                }
                            }


                            *//* BmobCourseContentBean().apply {
                                 this.id = course.id

                                 this.result = url
                             }.save(object :SaveListener<String>(){
                                 override fun done(p0: String?, p1: BmobException?) {

                                 }
                             })
 *//*

                        } else {
                            *//* val id = Uri.parse(course.uri).lastPathSegment
                             id?.let {uri->
                                 BmobCourseContentBean().apply {
                                     this.id = course.id
                                     this.result = api.getContent(uri, TOKEN).toString()
                                 }.save(object :SaveListener<String>(){
                                     override fun done(p0: String?, p1: BmobException?) {

                                     }
                                 })

                             }*//*
                        }
                    }
                }

            }


        }*/


    }
}