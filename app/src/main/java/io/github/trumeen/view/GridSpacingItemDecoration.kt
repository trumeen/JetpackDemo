package io.github.trumeen.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils


class GridSpacingItemDecoration(
    var defaultSpanCount: Int = 2,
    var spacing: Int = 0,
    var includeEdge: Boolean = false
) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val lp =
            view.layoutParams as GridLayoutManager.LayoutParams
        val spanIndex = lp.spanIndex
        val spanSize = lp.spanSize
        val spanCount = defaultSpanCount / spanSize
        val column = spanIndex / spanSize // item column

        if((position%defaultSpanCount+1)*defaultSpanCount !=parent.childCount){
            when(spanIndex){
                0 -> {
                    outRect.top = spacing
                    outRect.right = spacing
                }
                1->{
                    outRect.top = spacing
                    outRect.bottom = spacing
                    outRect.right = spacing
                }
            }
        }

        LogUtils.d("position:$position spanIndex:$spanIndex spanSize:$spanSize spanCount:$spanCount column:$column outRect:${outRect.left} ${outRect.top} ${outRect.right} ${outRect.bottom}")
    }

}