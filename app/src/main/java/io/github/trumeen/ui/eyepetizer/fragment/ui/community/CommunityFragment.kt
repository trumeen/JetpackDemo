package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.trumeen.R
import io.github.trumeen.bean.Tab
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.ui.eyepetizer.EyepettizerMainActivity
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.DailyFragment
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.DiscoveryFragment
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendFragment
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_community.tabLayout
import kotlinx.android.synthetic.main.fragment_community.viewPager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

class CommunityFragment : BaseVmFragment<EyepettizerViewModel>() {

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(activity as EyepettizerMainActivity).get(viewModelClass())
    }

    override fun initData() {

        mViewModel.mTabs.value?.let { tabs ->
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager.adapter =
                object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                    override fun getItemCount(): Int {
                        return tabs.size
                    }

                    override fun createFragment(position: Int): Fragment {
                        return CommunityContentFragment.newInstance(tabs[position].apiUrl)
                    }
                }
            TabLayoutMediator(tabLayout, viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = tabs[position].name
                }).attach()

        }

        mViewModel.mTabs.observe(this,
            Observer<List<Tab>> { tabs ->
                if (viewPager.adapter == null) {
                    viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    viewPager.adapter =
                        object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                            override fun getItemCount(): Int {
                                return tabs.size
                            }

                            override fun createFragment(position: Int): Fragment {
                                return CommunityContentFragment.newInstance(tabs[position].apiUrl)
                            }
                        }
                    TabLayoutMediator(tabLayout, viewPager,
                        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                            tab.text = tabs[position].name
                        }).attach()
                }

            })

        lifecycleScope.launchWhenCreated {
            mViewModel.getTabs()
        }
    }


}