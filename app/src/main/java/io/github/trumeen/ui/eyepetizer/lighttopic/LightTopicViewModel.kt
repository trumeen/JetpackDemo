package io.github.trumeen.ui.eyepetizer.lighttopic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import io.github.trumeen.bean.RecommendBean
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.UiModel
import io.github.trumeen.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LightTopicViewModel : BaseViewModel() {

    val currentData = MutableLiveData<RecommendBean>()

    val title: MutableLiveData<String> = MutableLiveData()

    private val mRepository: LightTopicRepository = LightTopicRepository(this, viewModelScope)


    fun getLightTopic(id: Int): Flow<PagingData<UiModel>> {
        return mRepository.getTopicResult(id).map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.FooterItem("")
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                }
                null
            }
        }
    }
}
