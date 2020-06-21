package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel

class EyepetizerRecommendViewModel : BaseViewModel() {

    val itemDataSet = MutableLiveData<ObservableArrayList<RecommendItemBean>>()

    fun getData() {

        launch(block = {
            val recommendList = VideoApi.get("http://baobab.kaiyanapp.com/api/v5/")
                .getRecommendList("http://baobab.kaiyanapp.com/api/v5/index/tab/allRec")
            println("recommendList:$recommendList")
            itemDataSet.value?.addAll(recommendList.itemList)
        }, error = {
            println("异常:${it.message}")
        })

    }
}
