package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.trumeen.R
import io.github.trumeen.data.UiModel
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import kotlinx.android.synthetic.main.fragment_daily.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyFragment : BaseVmFragment<EyepettizerViewModel>() {

    lateinit var video: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    private var mCurrentPlayingIndex = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        video = VideoView(context)
        video.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        val recommendPagingAdapter = RecommendPagingAdapter()
        recycler_view.adapter = recommendPagingAdapter
        lifecycleScope.launch {
            mViewModel.getDailyPagingData().collectLatest {
                recommendPagingAdapter.submitData(it)
            }
        }
        recommendPagingAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> swipe_refresh_layout.isRefreshing = false
                is LoadState.Error -> swipe_refresh_layout.isRefreshing = false
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            recommendPagingAdapter.refresh()
        }

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val start = linearLayoutManager.findFirstVisibleItemPosition()
                        val firstItemPosition =
                            linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                        val visibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                        if ((firstItemPosition != mCurrentPlayingIndex && firstItemPosition != -1)) {
                            if (recommendPagingAdapter.getItemViewType(firstItemPosition) == recommendPagingAdapter.FOLLOW_CARD) {
                                if (video.parent != null) {
                                    (video.parent as ViewGroup).removeView(video)
                                }
                                val itemData = recommendPagingAdapter.getItemData(
                                    firstItemPosition
                                )
                                (recyclerView[firstItemPosition - start].findViewById<ConstraintLayout>(
                                    R.id.cl_video
                                )).addView(
                                    video
                                )
                                video.setVideoPath((itemData as UiModel.RecommendItem).recommendItemBean.data.content.data.playUrl)
                                video.start()
                                mCurrentPlayingIndex = firstItemPosition
                            } else {
                                video.start()
                            }
                        }else{
                            if(video.isShown){
                                video.start()
                            }
                        }
                    }

                    RecyclerView.SCROLL_STATE_DRAGGING -> {

                    }

                    RecyclerView.SCROLL_STATE_SETTLING -> {
                        video.pause()
                    }
                }
            }
        })
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            DailyFragment().apply {

            }
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }
}