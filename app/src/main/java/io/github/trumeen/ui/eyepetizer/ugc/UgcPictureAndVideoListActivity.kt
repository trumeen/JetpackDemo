package io.github.trumeen.ui.eyepetizer.ugc


import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.bean.DataX
import io.github.trumeen.bean.IMAGE_INFO
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_pic_list.*

class UgcPictureAndVideoListActivity : BaseVmActivity<UgcListViewModel>() {

    override fun layoutRes() = R.layout.activity_pic_list

    override fun initView() {


    }

    override fun initData() {
        val imageInfo = intent.getParcelableExtra<ImageInfoBean>(IMAGE_INFO)
        mViewModel.currentDataX.value = imageInfo
        mViewModel.mList.add(imageInfo)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = UgcListAdapter(mViewModel.mList, mViewModel)
    }

    override fun viewModelClass(): Class<UgcListViewModel> {
        return UgcListViewModel::class.java
    }
}