package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import com.chad.library.BR
import com.youth.banner.adapter.BannerAdapter
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.main.SampleAdapter

class ImageBannerAdapter(var datas:ObservableArrayList<RecommendItemBean>):
    BannerAdapter<RecommendItemBean,SampleAdapter.SampleViewHolder>(datas) {

    private val viewHolders: MutableList<SampleAdapter.SampleViewHolder> = mutableListOf()

    init {
        datas.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableArrayList<RecommendItemBean>>() {
            override fun onChanged(sender: ObservableArrayList<RecommendItemBean>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<RecommendItemBean>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRemoved(positionStart)
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<RecommendItemBean>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<RecommendItemBean>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<RecommendItemBean>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

        })
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): SampleAdapter.SampleViewHolder {
        val inflate = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent?.context),
            R.layout.item_banner,
            parent,
            false
        )
        val sampleViewHolder = SampleAdapter.SampleViewHolder(inflate.root)
        sampleViewHolder.setBinding(inflate)
        viewHolders.add(sampleViewHolder)
        sampleViewHolder.markCreated()
        return sampleViewHolder
    }

    override fun onBindView(
        holder: SampleAdapter.SampleViewHolder,
        data: RecommendItemBean?,
        position: Int,
        size: Int
    ) {
        holder.getBinding().setVariable(BR.recommendItem, datas[position])
        holder.getBinding().executePendingBindings()
    }

}