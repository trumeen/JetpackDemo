package io.github.trumeen.ui.main

import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class MultipleTypeAdapter<T>(open var datas: ObservableArrayList<T>) :
    RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    val bindings = HashMap<Int, Pair<ViewDataBinding, Int>>()
    private val viewHolders: MutableList<SampleAdapter.SampleViewHolder> = mutableListOf()

    init {
        datas.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
            override fun onChanged(sender: ObservableArrayList<T>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRemoved(positionStart)
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<T>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

        })
    }

     fun addType(viewType: Int, binding: Pair<ViewDataBinding, Int>){
         bindings[viewType] = binding
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

    override fun getItemCount(): Int {
        return datas.size
    }


    override fun onViewAttachedToWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: SampleAdapter.SampleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        holder.getBinding()
            .setVariable(bindings[getItemViewType(position)]!!.second, datas[position])
        holder.getBinding().executePendingBindings()
    }

    fun setLifecycleDestroyed() {
        viewHolders.forEach {
            it.markDestroyed()
        }
    }

}