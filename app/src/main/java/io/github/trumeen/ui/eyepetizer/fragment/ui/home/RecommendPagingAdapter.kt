package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.base.MultipleTypePagingAdapter
import io.github.trumeen.ui.main.SampleAdapter

open class RecommendPagingAdapter : MultipleTypePagingAdapter<RecommendItemBean>(DATA_COMPARATOR) {


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
    val ROAMING_CALENDAR_DAILY_CARD = 15
    val ROAMING_CALENDAR_WEEKLY_CARD = 16
    val VIDEO_BEAN_FOR_CLIENT = 17
    val UGC_VIDEO_BEAN = 17
    val UGC_PICTURE_BEAN = 18
    val ITEM_COLLECTION = 19
    val AUTO_PLAY_FOLLOWCARD = 20

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
            VIDEO_BEAN_FOR_CLIENT, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_follow_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )

        addType(
            UGC_VIDEO_BEAN, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_ugc_video_layout,
                    parent,
                    false
                ), BR.recommendItem
            )
        )

        addType(
            UGC_PICTURE_BEAN, Pair(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recommend_ugc_video_layout,
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

        addType(
            ROAMING_CALENDAR_DAILY_CARD, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_roaming_calendr_daily_card_layout, parent, false
                ), BR.recommendItem
            )
        )

        addType(
            ROAMING_CALENDAR_WEEKLY_CARD, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_roaming_calendr_weekly_card_layout, parent, false
                ), BR.recommendItem
            )
        )

        addType(
            ITEM_COLLECTION, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_item_collection_layout, parent, false
                ), BR.recommendItem
            )
        )

        addType(
            AUTO_PLAY_FOLLOWCARD, Pair(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_auto_play_followcard_layout, parent, false
                ), BR.recommendItem
            )
        )

        return super.onCreateViewHolder(parent, viewType)
    }


    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return FOOT_VIEW
        }
        val item = getItem(position)
        item?.run {
            return when (type) {
                "textCard" -> {
                    when (data.dataType) {
                        "TextCardWithRightAndLeftTitle" -> TEXT_CARD_WITH_RIGHT_LEFT_TITLE
                        "TextCardWithTagId" -> TEXT_CARD_WITH_TAG_ID
                        "TextCard" -> when (data.type) {
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
                    when (item.data.dataType) {
                        "TagBriefCard" -> BRIEF_CARD
                        "TopicBriefCard" -> TOPIC_BRIEF_CARD
                        else -> BRIEF_CARD
                    }
                }
                "horizontalScrollCard" -> {
                    when (data.dataType) {
                        "ItemCollection" -> ITEM_COLLECTION
                        "HorizontalScrollCard"
                        -> HORIZONTAL_SCROLL_CARD
                        else -> HORIZONTAL_SCROLL_CARD
                    }
                }
                "specialSquareCardCollection" -> SPECIAL_SQUARE_CARD_COLLECTION
                "columnCardList" -> COLUMN_CARD_LIST
                "roamingCalendarDailyCard" -> ROAMING_CALENDAR_DAILY_CARD
                "roamingCalendarWeeklyCard" -> ROAMING_CALENDAR_WEEKLY_CARD
                "communityColumnsCard" -> {
                    when (data.dataType) {
                        "FollowCard" -> {
                            when (data.content.data.dataType) {
                                "VideoBeanForClient" -> VIDEO_BEAN_FOR_CLIENT
                                "UgcVideoBean" -> UGC_VIDEO_BEAN
                                "UgcPictureBean" -> UGC_PICTURE_BEAN
                                else -> TEXT_CARD
                            }
                        }
                        else -> TEXT_CARD
                    }
                }
                "autoPlayFollowCard"->AUTO_PLAY_FOLLOWCARD
                else -> TEXT_CARD
            }
        }
        return TEXT_CARD
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        return if (itemCount > 0) itemCount + 1 else itemCount
    }

    companion object {
        val DATA_COMPARATOR = object : DiffUtil.ItemCallback<RecommendItemBean>() {
            override fun areItemsTheSame(
                oldItem: RecommendItemBean,
                newItem: RecommendItemBean
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RecommendItemBean,
                newItem: RecommendItemBean
            ): Boolean =
                oldItem == newItem
        }
    }
}