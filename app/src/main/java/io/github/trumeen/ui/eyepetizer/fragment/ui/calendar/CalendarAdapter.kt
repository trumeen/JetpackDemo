package io.github.trumeen.ui.eyepetizer.fragment.ui.calendar

import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.MultipleTypePagingAdapter
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendAdapter
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendPagingAdapter
import io.github.trumeen.ui.main.SampleAdapter

class CalendarAdapter() :
    RecommendPagingAdapter() {

    override fun onViewAttachedToWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams && (holder.layoutPosition == 0
                    || holder.layoutPosition == 1
                    || holder.layoutPosition == 2)
        ) {
            val p: StaggeredGridLayoutManager.LayoutParams =
                lp as StaggeredGridLayoutManager.LayoutParams
            p.isFullSpan = true
        }
    }

}