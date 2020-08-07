package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.MultipleTypeAdapter
import io.github.trumeen.ui.base.SampleAdapter

open class RecommendAdapter<T>(var mDatas: ObservableArrayList<RecommendItemBean>) :
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
    val TEXT_CARD_FOOTER = 10
    val HORIZONTAL_SCROLL_CARD = 11
    val SPECIAL_SQUARE_CARD_COLLECTION = 12
    val COLUMN_CARD_LIST = 13
    val FOOT_VIEW = 14


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
                TEXT_CARD_FOOTER, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_recommend_text_footer_layout,
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

        addType(
                HORIZONTAL_SCROLL_CARD, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_recommend_horizontal_scroll_layout,
                        parent,
                        false
                ), BR.recommendItem
        )
        )

        addType(
                SPECIAL_SQUARE_CARD_COLLECTION, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_recommend_special_square_layout,
                        parent,
                        false
                ), BR.recommendItem
        )
        )

        addType(
                COLUMN_CARD_LIST, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_recommend_column_card_layout,
                        parent,
                        false
                ), BR.recommendItem
        )
        )
        addType(
                FOOT_VIEW, Pair(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_foot_view, parent, false
                ), BR.recommendItem
        )
        )
        return super.onCreateViewHolder(parent, viewType)
    }


    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return FOOT_VIEW
        }
        return when (datas[position].type) {
            "textCard" -> {
                when (datas[position].data.dataType) {
                    "TextCardWithRightAndLeftTitle" -> TEXT_CARD_WITH_RIGHT_LEFT_TITLE
                    "TextCardWithTagId" -> TEXT_CARD_WITH_TAG_ID
                    "TextCard" -> when (datas[position].data.type) {
                        "footer3", "footer2" -> TEXT_CARD_FOOTER
                        else -> TEXT_CARD
                    }
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
            "horizontalScrollCard" -> HORIZONTAL_SCROLL_CARD
            "specialSquareCardCollection" -> SPECIAL_SQUARE_CARD_COLLECTION
            "columnCardList" -> COLUMN_CARD_LIST
            else -> TEXT_CARD
        }
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        return if (itemCount > 0) itemCount + 1 else itemCount
    }
}