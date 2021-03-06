package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.HomePageRepository
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class EyepetizerRecommendViewModel : BaseViewModel() {

    val itemDataSet = MutableLiveData<ObservableArrayList<RecommendItemBean>>()
//    val repository = HomePageRepository(VideoApi.get("http://baobab.kaiyanapp.com/api/v5/"))

    fun getData() {
        launch(block = {
            val recommendList = VideoApi.get("http://baobab.kaiyanapp.com/api/v5/")
                .getRecommendList("http://baobab.kaiyanapp.com/api/v5/index/tab/allRec")
            itemDataSet.value?.addAll(recommendList.itemList)
        }, error = {
            println("异常:${it.message}")
        })

    }

    /*fun getRecommendPagingData(url:String): Flow<PagingData<RecommendItemBean>> {
        return repository.getPageData(url)
    }*/
}
