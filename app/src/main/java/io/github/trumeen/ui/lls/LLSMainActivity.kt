package io.github.trumeen.ui.lls

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_l_l_s_main.*
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


    }
}