package io.github.trumeen.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils


class GridSpacingItemDecoration(
    var defaultSpanCount: Int = 2,
    var spacing: Int = 0,
    var orientation: Int = GridLayoutManager.VERTICAL
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
        val childCount = parent.adapter?.itemCount
        if ((position / defaultSpanCount + 1) * defaultSpanCount != childCount) {

            if (orientation == GridLayoutManager.HORIZONTAL) {
                when (spanIndex) {
                    0 -> {
                        outRect.top = spacing
                        outRect.right = spacing
                        outRect.bottom = spacing
                        outRect.left = 0
                    }
                    1 -> {
                        outRect.top = 0
                        outRect.bottom = spacing
                        outRect.right = spacing
                        outRect.left = 0
                    }
                }
            } else {
                when (spanIndex) {
                    0 -> {
                        outRect.top = spacing
                        outRect.right = spacing
                        outRect.bottom = 0
                        outRect.left = 0
                    }
                    1 -> {
                        outRect.top = spacing
                        outRect.bottom = 0
                        outRect.right = 0
                        outRect.left = 0
                    }
                }
            }


        } else {
            if (orientation == GridLayoutManager.HORIZONTAL) {
                when (spanIndex) {
                    0 -> {
                        outRect.top = spacing
                        outRect.right = 0
                        outRect.bottom = spacing
                        outRect.left = 0
                    }
                    1 -> {
                        outRect.top = 0
                        outRect.bottom = spacing
                        outRect.right = 0
                        outRect.left = 0
                    }
                }
            } else {
                when (spanIndex) {
                    0 -> {
                        outRect.top = spacing
                        outRect.right = spacing
                        outRect.bottom = 0
                        outRect.left = 0
                    }
                    1 -> {
                        outRect.top = spacing
                        outRect.bottom = 0
                        outRect.right = 0
                        outRect.left = 0
                    }
                }
            }

        }
    }

}