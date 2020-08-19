package io.github.trumeen.ui.lls

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.Course
import io.github.trumeen.ui.base.MultipleTypePagingAdapter
import io.github.trumeen.ui.base.SampleAdapter

const val TYPE_NORMAL = 1

class CourseListAdapter : MultipleTypePagingAdapter<Course>(DATA_COMPARATOR) {
    companion object {
        val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(
                oldItem: Course,
                newItem: Course
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: Course,
                newItem: Course
            ): Boolean =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {
        addType(TYPE_NORMAL,Pair(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_lls_course_list,
                parent,
                false
            ), BR.course
        ))
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_NORMAL
    }


}