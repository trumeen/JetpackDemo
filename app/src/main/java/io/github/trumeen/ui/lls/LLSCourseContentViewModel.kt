package io.github.trumeen.ui.lls

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import io.github.trumeen.bean.ContentItem
import io.github.trumeen.bean.CourseContentResultBean
import io.github.trumeen.data.LLSCourseContentRepository
import io.github.trumeen.ui.base.BaseViewModel

class LLSCourseContentViewModel : BaseViewModel() {

    val contentLiveData = MutableLiveData<ObservableArrayList<ContentItem>>()

    val currentAudioUrl = MutableLiveData<String>()

    init {
        contentLiveData.value = ObservableArrayList()
        currentAudioUrl.value = ""
    }

    var mRepository = LLSCourseContentRepository(viewModelScope)


    fun getCurseList(id: String) {
        launch(
            block = {
                contentLiveData.value?.addAll(
                    GsonUtils.fromJson(
                        mRepository.getContentData(id).results[0].result,
                        CourseContentResultBean::class.java
                    ).contentItems
                )
            }
        )

    }

}