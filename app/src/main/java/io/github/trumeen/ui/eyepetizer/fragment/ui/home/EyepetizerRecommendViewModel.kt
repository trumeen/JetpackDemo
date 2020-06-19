package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import io.github.trumeen.ui.base.BaseViewModel

class EyepetizerRecommendViewModel: BaseViewModel() {

    fun getData(){

        launch(block = {
           /* val recommendList = VideoApi.get()
                .getRecommendList("http://baobab.kaiyanapp.com/api/v5/index/tab/allRec")
            println("recommendList:${recommendList.itemList.size}")*/
        },error = {
            println("异常:${it.message}")
        })

    }
}
