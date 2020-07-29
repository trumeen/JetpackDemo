package io.github.trumeen.ui.eyepetizer.fragment.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.data.CalendarRepository
import io.github.trumeen.net.VideoApi
import io.github.trumeen.ui.base.BaseViewModel
import io.github.trumeen.ui.eyepetizer.video.EYEPETTIZER_BASE_URL
import kotlinx.coroutines.flow.Flow

class CalendarViewModel : BaseViewModel() {

    var mCalendarData: MutableLiveData<ArrayList<RecommendItemBean>> = MutableLiveData()

    val backState: MutableLiveData<Boolean> = MutableLiveData(false)


    init {
        mCalendarData.value = ArrayList()
    }

    private var repository: CalendarRepository =
        CalendarRepository(
            VideoApi.get(
                EYEPETTIZER_BASE_URL
            )
        )

    suspend fun getCalendarData(date: String) {
        mCalendarData.value?.clear()
        mCalendarData.value?.addAll(repository.getCalendarData(date))
    }

    fun getPagingData(): Flow<PagingData<RecommendItemBean>> {
        return repository.getPageData()
    }


}