package io.github.trumeen.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by EnegyJ on 2020/9/25 19:35.
 */
@Parcelize
data class ImageInfoBean(
    val id: Int,
    val dateType:String,
    val author: Owner,
    val comment: Consumption,
    val imgs: List<String>
) : Parcelable