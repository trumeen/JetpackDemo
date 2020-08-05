package io.github.trumeen.ui.eyepetizer.fragment.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.coroutines.flow.collectLatest

class NotificationsFragment : BaseVmFragment<NotificationsViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
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

    }

    override fun viewModelClass(): Class<NotificationsViewModel> {
        return NotificationsViewModel::class.java
    }
}