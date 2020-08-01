package io.github.trumeen.ui.eyepetizer.fragment.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.trumeen.R
import io.github.trumeen.databinding.ItemCalendarLayoutBinding
import io.github.trumeen.ui.base.BaseVmFragment
import io.github.trumeen.weight.CalendarView
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.item_calendar_layout.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : BaseVmFragment<CalendarViewModel>() {
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var mCalendarAdapter: CalendarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun layoutRes(): Int {
        return R.layout.fragment_calendar
    }

    override fun initData() {
        mCalendarAdapter = CalendarAdapter(mViewModel)
        recycler_view.run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = mCalendarAdapter
        }
        lifecycleScope.launch {
            mViewModel.getPagingData(Calendar.getInstance().time).collectLatest {
                mCalendarAdapter.submitData(it)
            }
        }
    }

    override fun initView() {
        iv_back.setOnClickListener {
            back()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun viewModelClass(): Class<CalendarViewModel> {
        return CalendarViewModel::class.java
    }
}