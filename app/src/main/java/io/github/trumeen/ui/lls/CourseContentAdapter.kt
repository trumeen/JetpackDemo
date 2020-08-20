package io.github.trumeen.ui.lls

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.ContentItem
import io.github.trumeen.ui.base.MultipleTypeAdapter
import io.github.trumeen.ui.base.SampleAdapter


const val TYPE_TEXT = 0
const val TYPE_AUDIO = 1
const val TYPE_PIC = 2

class CourseContentAdapter(var mDatas: ObservableArrayList<ContentItem>,val viewModel:LLSCourseContentViewModel) :
    MultipleTypeAdapter<ContentItem>(mDatas) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {

        addType(
            TYPE_TEXT, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_course_text, parent, false
                ), BR.content
            )
        )

        addType(
            TYPE_AUDIO, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_course_audio, parent, false
                ), BR.content
            )
        )

        addType(
            TYPE_PIC, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_course_pic, parent, false
                ), BR.content
            )
        )

        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        holder.getBinding().setVariable(BR.viewModel,viewModel)
        super.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {

        return when (datas[position].contentType) {
            TYPE_PIC -> TYPE_PIC
            TYPE_TEXT -> TYPE_TEXT
            TYPE_AUDIO -> TYPE_AUDIO
            else -> TYPE_TEXT
        }
    }

}