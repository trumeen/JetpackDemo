package io.github.trumeen.ui.eyepetizer.ugc


import android.view.MotionEvent
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.IMAGE_INFO
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.databinding.ActivityPicListBinding
import io.github.trumeen.extension.visible
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
        mViewModel.showDesc.value = true
        val binding =
            DataBindingUtil.setContentView<ActivityPicListBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = UgcListAdapter(mViewModel.mList, mViewModel)
        iv_back_home.setOnClickListener {
            onBackPressed()
        }
    }

    override fun viewModelClass(): Class<UgcListViewModel> {
        return UgcListViewModel::class.java
    }
}