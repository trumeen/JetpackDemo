package io.github.trumeen.ui.eyepetizer.fragment.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.data.NotificationUiModel
import io.github.trumeen.ui.base.MultipleTypePagingAdapter
import io.github.trumeen.ui.base.SampleAdapter

class NotificationAdapter : MultipleTypePagingAdapter<NotificationUiModel>(DATA_COMPARATOR) {

    val TYPE_TEXT = 1
    val FOOT_VIEW = 2

    companion object {
        val DATA_COMPARATOR = object : DiffUtil.ItemCallback<NotificationUiModel>() {
            override fun areItemsTheSame(
                oldItem: NotificationUiModel,
                newItem: NotificationUiModel
            ): Boolean =
                (oldItem is NotificationUiModel.NotificationContent && newItem is NotificationUiModel.NotificationContent && oldItem.title == newItem.title) ||
                        (oldItem is NotificationUiModel.FooterItem && newItem is NotificationUiModel.FooterItem && oldItem.desc == newItem.desc)

            override fun areContentsTheSame(
                oldItem: NotificationUiModel,
                newItem: NotificationUiModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {
        addType(
            TYPE_TEXT, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_notification_layout,
                    parent,
                    false
                ), BR.message
            )
        )
        addType(
            FOOT_VIEW, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_foot_view,
                    parent,
                    false
                ), BR.message
            )
        )
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {

        if (getItem(position) is NotificationUiModel.FooterItem) {
            return FOOT_VIEW
        }

        return TYPE_TEXT
    }


}