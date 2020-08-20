package io.github.trumeen.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class LLSCureseResultBean(
    val courseList: List<Course>,
    val serverTime: Int
)

@Parcelize
data class Course(
    val id: String,
    val level: Int,
    val startTime: Long,
    val status: Int,
    val tag: Int,
    val title: String,
    val type: Int,
    val uri: String
) : Parcelable