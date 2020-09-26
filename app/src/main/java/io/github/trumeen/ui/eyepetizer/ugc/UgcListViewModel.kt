package io.github.trumeen.ui.eyepetizer.ugc

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import androidx.paging.map
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.data.CommunityRepository
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import kotlinx.coroutines.flow.map

class UgcListViewModel : EyepettizerViewModel() {

    private val idList: ArrayList<Int> = ArrayList()


    fun getNextData() {


    }

    fun getIdList(currentId: Int) {
        mCommunityRepository.getCommunityRecData("http://baobab.kaiyanapp.com/api/v7/community/tab/rec").map { pagingData ->
            pagingData.filter {
                it.type == "communityColumnsCard"
            }.map {
                idList.add(it.id)
            }
        }
        idList.removeAll(idList.subList(0, idList.indexOf(currentId)))
    }

    val mList: ObservableArrayList<ImageInfoBean> = ObservableArrayList()
    var currentDataX: MutableLiveData<ImageInfoBean> = MutableLiveData()
    var picIndexText: MutableLiveData<String> = MutableLiveData()
    var showDesc: MutableLiveData<Boolean> = MutableLiveData()

}
