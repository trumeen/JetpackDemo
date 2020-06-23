package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_discovery.*


class DiscoveryFragment : BaseVmFragment<EyepetizerDiscoveryViewModel>() {

    var sampleAdapter: RecommendAdapter<RecommendItemBean>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.itemDataSet.value = ObservableArrayList()

        sampleAdapter = RecommendAdapter(
            mViewModel.itemDataSet.value!!
        )
        recycler_view.adapter = sampleAdapter

        mViewModel.getData()

    }

    override fun onDestroy() {
        super.onDestroy()
        sampleAdapter?.setLifecycleDestroyed()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DiscoveryFragment()
                .apply {
                }
    }

    override fun viewModelClass(): Class<EyepetizerDiscoveryViewModel> {

        return EyepetizerDiscoveryViewModel::class.java
    }
}