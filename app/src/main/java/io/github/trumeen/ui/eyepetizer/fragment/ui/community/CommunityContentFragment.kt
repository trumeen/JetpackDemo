package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.trumeen.R
import io.github.trumeen.data.UiModel
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendPagingAdapter
import kotlinx.android.synthetic.main.fragment_community_content.*
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommunityContentFragment : BaseVmFragment<EyepettizerViewModel>() {
    // TODO: Rename and change types of parameters

    private var mCurrentPlayingIndex: Int = 0
    private lateinit var mApiUrl: String
    lateinit var video: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mApiUrl = it.getString(ARG_PARAM1)
        }
        video = VideoView(context)
        video.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_content, container, false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun initData() {

        val communityAdapter = CommunityAdapter()
        val followAdapter = RecommendPagingAdapter()
        recycler_view.apply {
            layoutManager =
                if (mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                } else {
                    LinearLayoutManager(activity)
                }
            adapter =
                if (mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
                    communityAdapter
                } else {
                    followAdapter
                }
        }
        if (!mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
            recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            val linearLayoutManager =
                                recyclerView.layoutManager as LinearLayoutManager
                            val start = linearLayoutManager.findFirstVisibleItemPosition()
                            val firstItemPosition =
                                linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                            val visibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                            if ((firstItemPosition != mCurrentPlayingIndex && firstItemPosition != -1)) {
                                if (followAdapter.getItemViewType(firstItemPosition) == followAdapter.AUTO_PLAY_FOLLOWCARD) {
                                    if (video.parent != null) {
                                        (video.parent as ViewGroup).removeView(video)
                                    }
                                    val itemData = followAdapter.getItemData(
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
                            } else {
                                if (video.isShown) {
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
        lifecycleScope.launchWhenCreated {
            if (mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
                mViewModel.getCommunityRecData(mApiUrl).collectLatest {
                    communityAdapter.submitData(it)
                }
            } else {
                mViewModel.getCommunityData(mApiUrl).collectLatest {
                    followAdapter.submitData(it)
                }
            }
        }

        communityAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> swipe_refresh_layout.isRefreshing = false
                is LoadState.NotLoading -> swipe_refresh_layout.isRefreshing = false
            }
        }

        followAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> swipe_refresh_layout.isRefreshing = false
                is LoadState.NotLoading -> swipe_refresh_layout.isRefreshing = false
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            if (mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
                communityAdapter.refresh()
            } else {
                followAdapter.refresh()
            }
        }

    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }

    companion object {

        @JvmStatic
        fun newInstance(url: String) =
            CommunityContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, url)
                }
            }
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }
}