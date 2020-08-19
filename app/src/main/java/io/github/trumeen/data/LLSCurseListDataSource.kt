package io.github.trumeen.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.map
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import io.github.trumeen.bean.BmobCourseBean
import io.github.trumeen.bean.BmobCourseContentBean
import io.github.trumeen.bean.Course
import io.github.trumeen.net.VideoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


const val TOKEN =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MTMyMDI3MjQsInBvb2xfaWQiOiIyOTA5MzNlY2NkMmUzZDExYTkwZjY2YzZkYmZiYjBkZCIsInVzZXJfaWQiOjEyMjI5NjQ1N30.EaSdzRWKWUqk6QnW1mLWncUTSzWnTs2z8JIW08m13v8"

class LLSCurseListDataSource(private var api: VideoApi) :
    EyepetizerDataSource<Int, Course>() {

    private var lastPage: Int = 1


    override suspend fun loadData(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Course> {
        lastPage = params.key ?: 1
        val response = api.getCurseList(
            page = lastPage,
            token = TOKEN
        )
        return PagingSource.LoadResult.Page(
            data = response.courseList,
            prevKey = if (lastPage == 1) null else lastPage - 1,
            nextKey = if (response.courseList.isNullOrEmpty()) null else lastPage + 1
        )
    }
}