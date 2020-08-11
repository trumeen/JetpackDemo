package io.github.trumeen.ui.eyepetizer

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.databinding.ActivityEyepettizerMainBinding
import io.github.trumeen.extension.setStatusBarColor
import kotlinx.android.synthetic.main.activity_eyepettizer_main.*

class EyepettizerMainActivity : AppCompatActivity() {

    private lateinit var mViewModel: EyepettizerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setStatusBarColor(resources.getColor(R.color.color_bg))
        initViewModel()
        val binding = DataBindingUtil.setContentView<ActivityEyepettizerMainBinding>(
            this,
            R.layout.activity_eyepettizer_main
        )
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
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