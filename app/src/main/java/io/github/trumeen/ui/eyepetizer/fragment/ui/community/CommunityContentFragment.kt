package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmFragment
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
class CommunityContentFragment : BaseVmFragment<CommunityViewModel>() {
    // TODO: Rename and change types of parameters
    private lateinit var mApiUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mApiUrl = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_content, container, false)
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

        lifecycleScope.launchWhenCreated {
            mViewModel.getCommunityData(mApiUrl).collectLatest {
                if (mApiUrl.contains("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")) {
                    communityAdapter.submitData(it)
                } else {
                    followAdapter.submitData(it)
                }

            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param url Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommunityContentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(url: String) =
            CommunityContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, url)
                }
            }
    }

    override fun viewModelClass(): Class<CommunityViewModel> {
        return CommunityViewModel::class.java
    }
}