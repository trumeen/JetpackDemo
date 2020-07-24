package io.github.trumeen.ui.eyepetizer

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.databinding.ActivityEyepettizerMainBinding
import io.github.trumeen.ui.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_eyepettizer_main.*

class EyepettizerMainActivity : BaseVmActivity<EyepettizerViewModel>() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.setContentView<ActivityEyepettizerMainBinding>(
            this,
            layoutRes()
        )
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        binding.setVariable(BR.viewModel, mViewModel)
        super.onCreate(savedInstanceState)
    }

    fun test() {
        nav_view.isVisible = false
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_home_to_navigation_calendar)
    }

    override fun viewModelClass(): Class<EyepettizerViewModel> {
        return EyepettizerViewModel::class.java
    }

    override fun layoutRes() = R.layout.activity_eyepettizer_main

}