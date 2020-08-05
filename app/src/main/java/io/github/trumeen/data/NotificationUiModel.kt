package io.github.trumeen.data

sealed class NotificationUiModel {

    data class NotificationContent(
        val actionUrl: String,
        val content: String,
        val date: String,
        val icon: String,
        val id: Int,
        val ifPush: Boolean,
        val pushStatus: Int,
        val title: String,
        val viewed: Boolean
    ) : NotificationUiModel()

    data class FooterItem(val desc: String) : NotificationUiModel()
}