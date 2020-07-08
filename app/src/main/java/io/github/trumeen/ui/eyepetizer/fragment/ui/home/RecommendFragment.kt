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
import io.github.trumeen.ui.main.MultipleTypeAdapter
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecommendFragment : BaseVmFragment<EyepetizerRecommendViewModel>() {

    var sampleAdapter: RecommendAdapter<RecommendItemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       /*
       LiveData 实现方式 未实现分页加载 需要使用adapter去实现
        mViewModel.itemDataSet.value = ObservableArrayList()
        sampleAdapter = RecommendAdapter(
            mViewModel.itemDataSet.value!!
        )
        recycler_view.adapter = sampleAdapter
        mViewModel.getData()
        */
        //使用paging3 实现分页加载
        val recommendPagingAdapter = RecommendPagingAdapter()
        recycler_view.adapter = recommendPagingAdapter
        lifecycleScope.launch {
            mViewModel.getPagingData("http://baobab.kaiyanapp.com/api/v5/index/tab/allRec").collectLatest {
                recommendPagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sampleAdapter?.setLifecycleDestroyed()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecommendFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RecommendFragment()
                .apply {
                }
    }

    override fun viewModelClass(): Class<EyepetizerRecommendViewModel> {
        return EyepetizerRecommendViewModel::class.java
    }
}