package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_community.*

class CommunityFragment : BaseVmFragment<CommunityViewModel>() {

    override fun viewModelClass(): Class<CommunityViewModel> {
        return CommunityViewModel::class.java
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.text = mViewModel.text.value
    }

}