package io.github.trumeen.ui.eyepetizer.ugc

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import androidx.paging.map
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.bean.UgcInfoBean
import io.github.trumeen.data.CommunityRepository
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.EyepettizerViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UgcListViewModel : EyepettizerViewModel() {

    private val mUgcRepository = UgcPictureRepository()

    val idList: ArrayList<UgcInfoBean> = ArrayList()
    var nextPageUrl = ""
    private var mCurrentIndex = 0

    fun getNextData() {
        viewModelScope.launch {
            if (idList.size > mCurrentIndex) {
                println("getNextData--->id:${idList[mCurrentIndex].id}")
                val videoReplies =
                    mUgcRepository.getVideoReplies(
                        idList[mCurrentIndex].id,
                        idList[mCurrentIndex].type
                    )
                mList.add(
                    ImageInfoBean(
                        videoReplies.id,
                        videoReplies.dataType,
                        videoReplies.owner,
                        videoReplies.consumption,
                        videoReplies.urls,
                        videoReplies.description,
                        videoReplies.playUrl
                    )
                )
                mCurrentIndex++
            } else {

            }

        }

    }

    fun getIdList(currentId: Int) {
        mCommunityRepository.getCommunityRecData("http://baobab.kaiyanapp.com/api/v7/community/tab/rec")
            .map { pagingData ->
                pagingData.filter {
                    it.type == "communityColumnsCard"
                }.map {
                    idList.add(
                        UgcInfoBean(
                            it.id,
                            if (it.data.content.type == "ugcPicture") "ugc_picture" else "ugc_video"
                        )
                    )
                }
            }
    }

    val mList: ObservableArrayList<ImageInfoBean> = ObservableArrayList()
    var currentDataX: MutableLiveData<ImageInfoBean> = MutableLiveData()
    var picIndexText: MutableLiveData<String> = MutableLiveData()
    var showDesc: MutableLiveData<Boolean> = MutableLiveData()

}
