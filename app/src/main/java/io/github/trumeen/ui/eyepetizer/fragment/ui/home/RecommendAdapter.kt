package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.main.MultipleTypeAdapter
import io.github.trumeen.ui.main.SampleAdapter

class RecommendAdapter<T>(var mDatas: ObservableArrayList<RecommendItemBean>) :
    MultipleTypeAdapter<RecommendItemBean>(mDatas) {

    val TEXT_CARD = 0
    val FOLLOW_CARD = 1
    val BANNER = 2
    val INFORMATION_CARD = 3
    val VIDEO_SMALL_CARD = 4
    val UGC_CARD = 5
    val BRIEF_CARD = 6
    val TEXT_CARD_WITH_RIGHT_LEFT_TITLE = 7
    val TEXT_CARD_WITH_TAG_ID = 8
    val TOPIC_BRIEF_CARD = 9


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SampleAdapter.SampleViewHolder {
        addType(
            TEXT_CARD_WITH_RIGHT_LEFT_TITLE, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_text_with_right_left_title_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )

        addType(
            TEXT_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_text_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )

        addType(
            TEXT_CARD_WITH_TAG_ID, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_text_with_tag_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )


        addType(
            FOLLOW_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_follow_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        addType(
            BANNER, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_banner_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        addType(
            INFORMATION_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_information_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        addType(
            VIDEO_SMALL_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_video_small_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        addType(
            UGC_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_ugc_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        addType(
            BRIEF_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_brief_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )

        addType(
            TOPIC_BRIEF_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_topic_brief_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }


    override fun getItemViewType(position: Int): Int {
        return when (datas[position].type) {
            "textCard" -> {
                when (datas[position].data.dataType) {
                    "TextCardWithRightAndLeftTitle" -> TEXT_CARD_WITH_RIGHT_LEFT_TITLE
                    "TextCardWithTagId" -> TEXT_CARD_WITH_TAG_ID
                    "TextCard" -> TEXT_CARD
                    else -> TEXT_CARD
                }
            }
            "followCard" -> FOLLOW_CARD
            "banner" -> BANNER
            "informationCard" -> INFORMATION_CARD
            "videoSmallCard" -> VIDEO_SMALL_CARD
            "ugcSelectedCardCollection" -> UGC_CARD
            "briefCard" -> {
                when (datas[position].data.dataType) {
                    "TagBriefCard" -> BRIEF_CARD
                    "TopicBriefCard" -> TOPIC_BRIEF_CARD
                    else -> BRIEF_CARD
                }
            }
            else -> TEXT_CARD
        }
    }
}