package io.github.trumeen.ui.eyepetizer.lighttopic

import android.os.Bundle
import android.view.ViewGroup
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.databinding.ActivityLightTopicBinding
import io.github.trumeen.ui.base.BaseVmActivity
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendPagingAdapter
import kotlinx.android.synthetic.main.activity_light_topic.*
import kotlinx.android.synthetic.main.activity_light_topic.recycler_view
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LightTopicActivity : BaseVmActivity<LightTopicViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityLightTopicBinding>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        intent.data?.apply {
            if (query != null) {
                val title = getQueryParameter("title")
                val id = lastPathSegment
                mViewModel.title.value = title
                id?.let {
                    val recommendPagingAdapter = RecommendPagingAdapter()
                    recycler_view.adapter = recommendPagingAdapter
                    lifecycleScope.launch {
                        mViewModel.getLightTopic(it.toInt())
                            .collectLatest { pagingData ->
                                recommendPagingAdapter.submitData(pagingData)
                            }
                    }
                }

            }
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun layoutRes(): Int = R.layout.activity_light_topic

    override fun viewModelClass(): Class<LightTopicViewModel> {
        return LightTopicViewModel::class.java
    }
}