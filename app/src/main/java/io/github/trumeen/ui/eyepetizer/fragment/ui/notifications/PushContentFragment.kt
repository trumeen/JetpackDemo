package io.github.trumeen.ui.eyepetizer.fragment.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import kotlinx.android.synthetic.main.fragment_push_content.*
import kotlinx.coroutines.flow.collectLatest


private const val ARG_PARAM1 = "param1"

class PushContentFragment : BaseVmFragment<EyepettizerViewModel>() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_push_content, container, false)
    }


    override fun initData() {
        val notificationAdapter = NotificationAdapter()
        recycler_view.apply {
            adapter = notificationAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.getMessageList().collectLatest {
                notificationAdapter.submitData(it)
            }
        }
        notificationAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.NotLoading -> swipe_refresh_layout.isRefreshing = false
                is LoadState.Error -> swipe_refresh_layout.isRefreshing = false
            }
        }
        swipe_refresh_layout.setOnRefreshListener {
            notificationAdapter.refresh()
        }

    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            PushContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, url)
                }
            }
    }
}