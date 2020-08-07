package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.RecommendPagingAdapter
import io.github.trumeen.ui.base.SampleAdapter

class CommunityAdapter : RecommendPagingAdapter() {

    override fun onViewAttachedToWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams && (holder.layoutPosition == 0
                    || holder.layoutPosition == 1)
        ) {
            val p: StaggeredGridLayoutManager.LayoutParams = lp
            p.isFullSpan = true
        }
    }
}