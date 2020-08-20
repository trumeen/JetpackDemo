package io.github.trumeen.data

import androidx.paging.PagingSource
import io.github.trumeen.bean.Course
import io.github.trumeen.net.VideoApi


class BmobCurseListDataSource(private var api: VideoApi) :
    EyepetizerDataSource<Int, Course>() {

    private var lastOffset: Int = 0

    private var limit: Int = 20


    override suspend fun loadData(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Course> {
        lastOffset = params.key ?: 0
        val response = api.getBmobCurseList(
            offset = lastOffset,
            limit = limit
        )
        return PagingSource.LoadResult.Page(
            data = response.results,
            prevKey = if (lastOffset == 0) null else lastOffset - limit,
            nextKey = if (response.results.isNullOrEmpty()) null else lastOffset + limit
        )
    }
}