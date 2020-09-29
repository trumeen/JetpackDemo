package io.github.trumeen.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.github.trumeen.BR

/**
 * Created by EnegyJ on 2020/9/29 17:19.
 */
abstract class BaseDataBindingActivity<VM : BaseViewModel, VB : ViewDataBinding> : BaseVmActivity<VM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<VB>(this, layoutRes())
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this
    }

}