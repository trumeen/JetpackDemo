package io.github.trumeen.ui.eyepetizer.fragment.ui.home

import com.chad.library.adapter.base.entity.MultiItemEntity

class DiscoveryItem:MultiItemEntity {
    override val itemType: Int
        get() = TODO("Not yet implemented")

    companion object {
        const val TYPE_TEXT_CARD = -100
        const val  TYPE_FOLLOW_CARD= -99
        const val TYPE_INFORMATION_CARD = -98
        const val TYPE_VIDEO_SMALL_CARD = -97
        const val TYPE_UGC_SELECTED_CARD_COLLECTION = -96
        const val TYPE_BRIEF_CARD = -95
    }
}
