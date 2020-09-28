package io.github.trumeen.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Trumeen on 2020/9/25 19:35.
 */
@Parcelize
data class ImageInfoBean(
    val id: Int,
    val dateType: String,
    val owner: Owner,
    val comment: Consumption,
    val imgs: List<String>?,
    val description: String?,
    val playUrl: String?
) : Parcelable