package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.lifecycleScope
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_daily.*
import kotlinx.android.synthetic.main.fragment_daily.recycler_view
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyFragment : BaseVmFragment<EyepetizerDailyViewModel>() {

    var sampleAdapter: RecommendAdapter<RecommendItemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       /* mViewModel.itemDataSet.value = ObservableArrayList()

        sampleAdapter = RecommendAdapter(
            mViewModel.itemDataSet.value!!
        )
        recycler_view.adapter = sampleAdapter

        mViewModel.getData()*/
        //使用paging3 实现分页加载
        val recommendPagingAdapter = RecommendPagingAdapter()
        recycler_view.adapter = recommendPagingAdapter
        lifecycleScope.launch {
            mViewModel.getPagingData("http://baobab.kaiyanapp.com/api/v5/index/tab/feed").collectLatest {
                recommendPagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sampleAdapter?.setLifecycleDestroyed()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DailyFragment().apply {

            }
    }

    override fun viewModelClass(): Class<EyepetizerDailyViewModel> {
        return EyepetizerDailyViewModel::class.java
    }
}