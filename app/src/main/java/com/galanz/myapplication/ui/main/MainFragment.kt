package com.galanz.myapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.galanz.myapplication.BR
import com.galanz.myapplication.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var sampleAdapter: SampleAdapter<Person>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mListDatas.value = ObservableArrayList<Person>()
        (0..10).forEach {
            viewModel.mListDatas.value?.add(Person("hello world:$it", it,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1285688348,3913025006&fm=26&gp=0.jpg"))

        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        sampleAdapter =
            SampleAdapter(viewModel.mListDatas.value!!, R.layout.item_layout, BR.itemData,BR.viewModel,viewModel)
        recycler_view.adapter = sampleAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        sampleAdapter?.setLifecycleDestroyed()
    }

}