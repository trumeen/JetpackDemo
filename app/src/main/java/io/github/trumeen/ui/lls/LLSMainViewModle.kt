package io.github.trumeen.ui.lls

import android.database.Cursor
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import io.github.trumeen.bean.Course
import io.github.trumeen.data.LLSRepository
import io.github.trumeen.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class LLSMainViewModle : BaseViewModel() {

    var mRepository = LLSRepository(viewModelScope)


    fun getCurseList(): Flow<PagingData<Course>> {
        return mRepository.getPageData()
    }

}
