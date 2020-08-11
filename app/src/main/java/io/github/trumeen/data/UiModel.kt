package io.github.trumeen.data

import io.github.trumeen.bean.RecommendItemBean

sealed class UiModel {
    data class RecommendItem(val recommendItemBean: RecommendItemBean) : UiModel()

    data class HeaderItem(val description: String) : UiModel()

    data class FooterItem(val description: String) : UiModel()

}