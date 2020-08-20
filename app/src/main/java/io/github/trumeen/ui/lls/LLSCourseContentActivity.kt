package io.github.trumeen.ui.lls

import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.bean.Course
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_l_l_s_course_content.*

const val COURSE_DATA = "course_data"

class LLSCourseContentActivity : BaseVmActivity<LLSCourseContentViewModel>() {

    override fun layoutRes() = R.layout.activity_l_l_s_course_content

    var courseAdapter: CourseContentAdapter? = null

    override fun viewModelClass(): Class<LLSCourseContentViewModel> {
        return LLSCourseContentViewModel::class.java
    }

    override fun initView() {
        tv_title.text = intent.getParcelableExtra<Course>(COURSE_DATA).title
        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun initData() {
        courseAdapter = CourseContentAdapter(mViewModel.contenLiveData.value!!,mViewModel)
        recycler_view.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(this@LLSCourseContentActivity)
        }
        mViewModel.getCurseList(intent.getParcelableExtra<Course>(COURSE_DATA).id)
    }

    override fun onDestroy() {
        courseAdapter?.setLifecycleDestroyed()
        super.onDestroy()

    }


}