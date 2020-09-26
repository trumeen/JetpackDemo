package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecommendFragment : BaseVmFragment<EyepettizerViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recommendPagingAdapter = RecommendPagingAdapter()
        recycler_view.adapter = recommendPagingAdapter
        lifecycleScope.launch {
            mViewModel.getRecommendPagingData()
                .collectLatest {
                    recommendPagingAdapter.submitData(it)
                }
        }

        recommendPagingAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> swipe_refresh_layout.isRefreshing = false
                is LoadState.Error -> {
                    swipe_refresh_layout?.isRefreshing = false
                }
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            recommendPagingAdapter.refresh()
        }
        /*mViewModel.getRecommendPagingLivingData("http://baobab.kaiyanapp.com/api/v5/index/tab/allRec")
            .observe(
                activity as EyepettizerMainActivity,
                Observer<PagingData<RecommendItemBean>> { t ->
                    t?.let {
                        lifecycleScope.launch {
                            recommendPagingAdapter.submitData(it)
                        }

                    }
                }
            )*/

    }


    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendFragment()
                .apply {
                }
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }
}