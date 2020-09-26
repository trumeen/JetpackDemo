package io.github.trumeen.ui.eyepetizer.ugc


import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.ID_LIST
import io.github.trumeen.bean.IMAGE_INFO
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.bean.START_NEXT_PAGE
import io.github.trumeen.databinding.ActivityPicListBinding
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_pic_list.*

class UgcPictureAndVideoListActivity : BaseVmActivity<UgcListViewModel>() {

    override fun layoutRes() = R.layout.activity_pic_list

    override fun initView() {

    }

    override fun initData() {
        val imageInfo = intent.getParcelableExtra<ImageInfoBean>(IMAGE_INFO)
        val nextPageUrl = intent.getStringExtra(START_NEXT_PAGE)
        val idList = intent.getIntegerArrayListExtra(ID_LIST)

        println("nextPageUrl:${nextPageUrl} idList:${idList}")

        mViewModel.currentDataX.value = imageInfo
        mViewModel.mList.add(imageInfo)
        mViewModel.showDesc.value = true
        val binding =
            DataBindingUtil.setContentView<ActivityPicListBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = UgcListAdapter(mViewModel.mList, mViewModel)
        PagerSnapHelper().attachToRecyclerView(recycler_view)
        iv_back_home.setOnClickListener {
            onBackPressed()
        }
        mViewModel.getNextData()
    }

    override fun viewModelClass(): Class<UgcListViewModel> {
        return UgcListViewModel::class.java
    }
}