package io.github.trumeen.ui.lls

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import io.github.trumeen.R
import io.github.trumeen.bean.BmobCourseBean
import io.github.trumeen.bean.BmobCourseContentBean
import io.github.trumeen.data.LLS_BASE_URL
import io.github.trumeen.data.TOKEN
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_l_l_s_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        GlobalScope.launch(Dispatchers.IO) {
            val api = VideoApi.getLLS(LLS_BASE_URL)
            (1..12).forEach {
                val curseList = api
                    .getCurseList(token = TOKEN, page = it)
                if(!curseList.courseList.isNullOrEmpty()){
                    curseList.courseList.forEach { course->
                        println("title-->${course.title}")
                        if (course.uri.isNullOrBlank()) {
                            BmobCourseContentBean().apply {
                                this.id = course.id
                                this.result = api.getVideoInfo(course.id, TOKEN).toString()
                            }.save(object :SaveListener<String>(){
                                override fun done(p0: String?, p1: BmobException?) {

                                }
                            })
                        } else {
                            val id = Uri.parse(course.uri).lastPathSegment
                            id?.let {uri->
                                BmobCourseContentBean().apply {
                                    this.id = course.id
                                    this.result = api.getContent(uri, TOKEN).toString()
                                }.save(object :SaveListener<String>(){
                                    override fun done(p0: String?, p1: BmobException?) {

                                    }
                                })

                            }
                        }
                    }
                }

            }


        }


    }
}