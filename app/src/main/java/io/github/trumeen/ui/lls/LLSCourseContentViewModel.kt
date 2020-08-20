package io.github.trumeen.ui.lls

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken
import io.github.trumeen.bean.ContentItem
import io.github.trumeen.bean.Course
import io.github.trumeen.bean.CourseContentResultBean
import io.github.trumeen.data.LLSCourseContentRepository
import io.github.trumeen.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class LLSCourseContentViewModel : BaseViewModel() {

    val contenLiveData = MutableLiveData<ObservableArrayList<ContentItem>>()

    init {
        contenLiveData.value = ObservableArrayList()

    }

    var mRepository = LLSCourseContentRepository(viewModelScope)


    fun getCurseList(id: String) {
        launch(
            block = {
                contenLiveData.value?.addAll(
                    GsonUtils.fromJson(
                        mRepository.getContentData(id).results[0].result,
                        CourseContentResultBean::class.java
                    ).contentItems
                )
            }
        )

    }

}