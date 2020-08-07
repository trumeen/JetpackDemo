package io.github.trumeen.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

open class MultipleTypePagingAdapter<T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, SampleAdapter.SampleViewHolder>(diffCallback) {
    val bindings = HashMap<Int, Pair<ViewDataBinding, Int>>()
    private val viewHolders: MutableList<SampleAdapter.SampleViewHolder> = mutableListOf()

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        if (position < itemCount-1) {
            holder.getBinding()
                .setVariable(bindings[getItemViewType(position)]!!.second, getItem(position))
        }
        holder.getBinding().executePendingBindings()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {
        val viewDataBinding = bindings[viewType]
        val element = SampleAdapter.SampleViewHolder(viewDataBinding!!.first.root)
        viewHolders.add(element)
        element.setBinding(viewDataBinding.first)
        element.markCreated()
        return element
    }

    fun addType(viewType: Int, binding: Pair<ViewDataBinding, Int>) {
        bindings[viewType] = binding
    }

    override fun onViewAttachedToWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }


    fun setLifecycleDestroyed() {
        viewHolders.forEach {
            it.markDestroyed()
        }
    }
}