package io.github.trumeen.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoInfoBean(
    val videoId: Int,
    val coverUrl: String,
    val title: String,
    val description: String,
    val category: String,
    val consumption: Consumption,
    val author: Author
) : Parcelable

