package io.github.trumeen.ui.eyepetizer

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.databinding.ActivityEyepettizerMainBinding
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_eyepettizer_main.*

class EyepettizerMainActivity : AppCompatActivity() {

    private lateinit var mViewModel: EyepettizerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        val binding = DataBindingUtil.setContentView<ActivityEyepettizerMainBinding>(
            this,
            R.layout.activity_eyepettizer_main
        )
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
        mViewModel.mBottomButtonState.observe(this,
            Observer<Boolean> {
                println("value Change:${it}")
            })
        println("EyepettizerMainActivity onCreate $mViewModel")
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    fun test() {
        mViewModel.mBottomButtonState.value = false
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_home_to_navigation_calendar)
    }

    fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }


}