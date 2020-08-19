package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.Course
import io.github.trumeen.net.VideoApi

class LLSCurseListDataSource(private var api: VideoApi) :
    EyepetizerDataSource<Int, Course>() {

    private var lastPage: Int = 1


    override suspend fun loadData(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Course> {
        val page: Int = params.key ?: 1
        val response = api.getCurseList(
            page = page,
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MTMxODU5MTcsInBvb2xfaWQiOiIyOTA5MzNlY2NkMmUzZDExYTkwZjY2YzZkYmZiYjBkZCIsInVzZXJfaWQiOjEyMjI5NjQ1N30.iuiqQvWocwed17v19Jusu5gVFvH6yich2_4ckvAYyG4"
        )
        return PagingSource.LoadResult.Page(
            data = response.courseList,
            prevKey = if (lastPage == 1) 1 else lastPage - 1,
            nextKey = if (response.courseList.isNullOrEmpty()) null else lastPage + 1
        )
    }
}