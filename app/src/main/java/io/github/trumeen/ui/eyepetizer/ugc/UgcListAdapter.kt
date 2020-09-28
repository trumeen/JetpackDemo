package io.github.trumeen.ui.eyepetizer.ugc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.DataX
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.bean.IndicateTextBean
import io.github.trumeen.ui.base.MultipleTypeAdapter
import io.github.trumeen.ui.base.SampleAdapter

/**
 * Created by Trumeen on 2020/9/25 15:38.
 */
class UgcListAdapter(mDatas: ObservableArrayList<ImageInfoBean>, var viewModel: UgcListViewModel) :
    MultipleTypeAdapter<ImageInfoBean>(mDatas) {

    val UGC_PICTURE_BEAN_MULTI = 1
    val UGC_PICTURE_BEAN_SIGNAL = 2
    val UGC_VIDEO_BEAN = 3


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {
        when (viewType) {
            UGC_PICTURE_BEAN_MULTI -> {
                addType(
                    UGC_PICTURE_BEAN_MULTI, Pair(
                        DataBindingUtil.inflate<ViewDataBinding>(
                            LayoutInflater.from(parent.context),
                            R.layout.item_ugc_picture_layout,
                            parent,
                            false
                        ), BR.item
                    )
                )
            }
            UGC_PICTURE_BEAN_SIGNAL -> {
                addType(
                    UGC_PICTURE_BEAN_SIGNAL, Pair(
                        DataBindingUtil.inflate<ViewDataBinding>(
                            LayoutInflater.from(parent.context),
                            R.layout.item_ugc_picture_signal_layout,
                            parent,
                            false
                        ), BR.item
                    )
                )
            }
            UGC_VIDEO_BEAN -> {
                addType(
                    UGC_VIDEO_BEAN, Pair(
                        DataBindingUtil.inflate<ViewDataBinding>(
                            LayoutInflater.from(parent.context),
                            R.layout.item_ugc_video_signal_layout,
                            parent,
                            false
                        ), BR.item
                    )
                )
            }
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        holder.getBinding().apply {
            setVariable(bindings[getItemViewType(position)]!!.second, datas[position])
            setVariable(BR.viewModel, viewModel)
            if (getItemViewType(position) == UGC_PICTURE_BEAN_MULTI) {
                val indicateTextBean = IndicateTextBean(MutableLiveData())
                indicateTextBean.data.value = "1/${datas[position].imgs?.size}"
                setVariable(BR.indicat, indicateTextBean)
            }
        }
        holder.getBinding().executePendingBindings()
    }


    override fun getItemViewType(position: Int): Int {
        val item = datas[position]
        return when (item?.dateType) {
            "UgcPictureBean" -> {
                if (item.imgs!!.size > 1) UGC_PICTURE_BEAN_MULTI
                else UGC_PICTURE_BEAN_SIGNAL
            }
            "UgcVideoBean" -> {
                UGC_VIDEO_BEAN
            }
            else -> UGC_PICTURE_BEAN_SIGNAL
        }
    }

}