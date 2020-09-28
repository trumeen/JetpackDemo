package io.github.trumeen.ui.eyepetizer.ugc


import android.text.method.ScrollingMovementMethod
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.*
import io.github.trumeen.databinding.ActivityPicListBinding
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_pic_list.*

class UgcPictureAndVideoListActivity : BaseVmActivity<UgcListViewModel>() {

    var lastItem = 0
    override fun layoutRes() = R.layout.activity_pic_list

    override fun initView() {

    }

    override fun initData() {
        val imageInfo = intent.getParcelableExtra<ImageInfoBean>(IMAGE_INFO)
        val nextPageUrl = intent.getStringExtra(START_NEXT_PAGE)
        val idList = intent.getParcelableArrayListExtra<UgcInfoBean>(ID_LIST)
        mViewModel.idList.addAll(idList)
        mViewModel.nextPageUrl = nextPageUrl
        mViewModel.currentDataX.value = imageInfo
        mViewModel.mList.add(imageInfo)
        mViewModel.showDesc.value = true
        val binding =
            DataBindingUtil.setContentView<ActivityPicListBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = UgcListAdapter(mViewModel.mList, mViewModel)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(recycler_view)
        iv_back_home.setOnClickListener {
            onBackPressed()
        }
        tv_desc.movementMethod = ScrollingMovementMethod()
        mViewModel.getNextData()
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val currentItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    if (currentItem != -1) {
                        mViewModel.currentDataX.value = mViewModel.mList[currentItem]
                        if (lastItem != currentItem) {
                            tv_desc.scrollTo(0, 0)
                            mViewModel.showDesc.value = true
                            lastItem = currentItem
                        }
                        if (currentItem == mViewModel.mList.size - 1 && currentItem != 0) {
                            mViewModel.getNextData()
                        }
                    }

                }
            }
        })
    }

    override fun viewModelClass(): Class<UgcListViewModel> {
        return UgcListViewModel::class.java
    }
}