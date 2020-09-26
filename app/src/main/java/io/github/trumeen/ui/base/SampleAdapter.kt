package io.github.trumeen.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

open class SampleAdapter<T>(
    var datas: ObservableArrayList<T>,
    var layoutRes: Int,
    var brId: Int,
    var viewModelId: Int = 0,
    var viewModel: ViewModel? = null
) :
    RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {
    val viewHolders: MutableList<SampleViewHolder> = mutableListOf()

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val inflate = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
        val sampleViewHolder =
            SampleViewHolder(inflate.root)
        sampleViewHolder.setBinding(inflate)
        viewHolders.add(sampleViewHolder)
        sampleViewHolder.markCreated()
        return sampleViewHolder
    }

    override fun onViewAttachedToWindow(holder: SampleViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: SampleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }


    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.getBinding().setVariable(brId, datas[position])
        viewModel?.apply {
            holder.getBinding().setVariable(viewModelId, this)
        }
        holder.getBinding().executePendingBindings()
    }

    fun setLifecycleDestroyed() {
        viewHolders.forEach {
            it.markDestroyed()
        }
    }

    class SampleViewHolder(root: View) : RecyclerView.ViewHolder(root), LifecycleOwner {

        lateinit var dataBinding: ViewDataBinding

        private val lifecycleRegistry = LifecycleRegistry(this)
        private var wasPaused: Boolean = false

        init {
            lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        }

        fun markCreated() {
            lifecycleRegistry.currentState = Lifecycle.State.CREATED
        }

        fun markAttach() {
            if (wasPaused) {
                lifecycleRegistry.currentState = Lifecycle.State.RESUMED
                wasPaused = false
            } else {
                lifecycleRegistry.currentState = Lifecycle.State.STARTED
            }
        }

        fun markDetach() {
            wasPaused = true
            lifecycleRegistry.currentState = Lifecycle.State.CREATED
        }

        fun markDestroyed() {
            lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        }

        fun getBinding(): ViewDataBinding {
            return dataBinding
        }

        fun setBinding(binding: ViewDataBinding) {
            this.dataBinding = binding
            dataBinding.lifecycleOwner = this
        }

        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }

    }
}