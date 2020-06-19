package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.trumeen.R
import io.github.trumeen.ui.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_discovery.*


/**
 * A simple [Fragment] subclass.
 * Use the [DiscoveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoveryFragment : BaseVmFragment<EyepetizerDiscoveryViewModel>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DiscoveryFragment()
                .apply {
            }
    }

    override fun viewModelClass(): Class<EyepetizerDiscoveryViewModel> {

        return EyepetizerDiscoveryViewModel::class.java
    }
}