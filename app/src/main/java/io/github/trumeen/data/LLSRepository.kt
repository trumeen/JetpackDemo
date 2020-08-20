package io.github.trumeen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.trumeen.bean.Course
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

const val LLS_BASE_URL = "https://apineo.llsapp.com/api/v1/"
const val BMOB_BASE_URL = "https://api2.bmob.cn/1/classes/"

class LLSRepository(viewModelScope: CoroutineScope) {


/*    private var mPager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {
            LLSCurseListDataSource(
                VideoApi.getBmob(LLS_BASE_URL))
        }
    ).flow.cachedIn(viewModelScope)*/

    private var mPager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {
            BmobCurseListDataSource(
                VideoApi.getBmob(BMOB_BASE_URL))
        }
    ).flow.cachedIn(viewModelScope)

    fun getPageData(): Flow<PagingData<Course>> {
        return mPager

    }
}
