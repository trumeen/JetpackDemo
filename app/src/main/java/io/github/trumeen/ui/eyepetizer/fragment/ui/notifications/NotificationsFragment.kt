package io.github.trumeen.ui.eyepetizer.fragment.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import io.github.trumeen.R
import io.github.trumeen.bean.Tab
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.community.CommunityContentFragment
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.tabLayout
import kotlinx.android.synthetic.main.fragment_notifications.viewPager
import kotlinx.coroutines.flow.collectLatest

class NotificationsFragment : BaseVmFragment<EyepettizerViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }


    override fun initData() {

        mViewModel.mMessagesTabs.value?.let { tabs ->
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager.adapter =
                object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                    override fun getItemCount(): Int {
                        return tabs.size
                    }

                    override fun createFragment(position: Int): Fragment {
                        return when (position) {
                            0 -> PushContentFragment.newInstance(tabs[position].apiUrl)
                            1 -> InteractiveFragment.newInstance(tabs[position].apiUrl)
                            else -> MessagesFragment.newInstance(tabs[position].apiUrl)
                        }
                    }
                }
            TabLayoutMediator(tabLayout, viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = tabs[position].name
                }).attach()

        }

        mViewModel.mMessagesTabs.observe(this,
            Observer<List<Tab>> { tabs ->
                if (viewPager.adapter == null) {
                    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    viewPager.adapter =
                        object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                            override fun getItemCount(): Int {
                                return tabs.size
                            }

                            override fun createFragment(position: Int): Fragment {
                                return when (position) {
                                    0 -> PushContentFragment.newInstance(tabs[position].apiUrl)
                                    1 -> InteractiveFragment.newInstance(tabs[position].apiUrl)
                                    else -> MessagesFragment.newInstance(tabs[position].apiUrl)
                                }

                            }
                        }
                    TabLayoutMediator(tabLayout, viewPager,
                        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                            tab.text = tabs[position].name
                        }).attach()
                }

            })
        mViewModel.getMessagesTabs()

    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }
}