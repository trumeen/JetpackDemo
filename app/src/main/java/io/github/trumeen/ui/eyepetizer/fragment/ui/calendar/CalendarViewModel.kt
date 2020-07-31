package io.github.trumeen.ui.eyepetizer.fragment.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.CalendarRepository
import io.github.trumeen.data.UiModel
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import kotlin.collections.ArrayList

class CalendarViewModel : BaseViewModel() {

    var mCalendarData: MutableLiveData<ArrayList<RecommendItemBean>> = MutableLiveData()


    init {
        mCalendarData.value = ArrayList()
    }

    private var repository: CalendarRepository =
        CalendarRepository(
            VideoApi.get(EYEPETTIZER_BASE_URL)
        )

    suspend fun getCalendarData(date: String) {
        mCalendarData.value?.clear()
        mCalendarData.value?.addAll(repository.getCalendarData(date))
    }

    fun getPagingData(date: Date): Flow<PagingData<UiModel>> {
        return repository.getPageData(date).map { pagingDate ->
            pagingDate.map {
                UiModel.RecommendItem(it)
            }
        }.map {
            it.insertSeparators<UiModel.RecommendItem, UiModel> { before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators null
                }
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators UiModel.HeaderItem("")
                }
                null
            }
        }

    }


}