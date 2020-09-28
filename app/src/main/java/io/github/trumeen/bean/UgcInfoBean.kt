package io.github.trumeen.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by EnegyJ on 2020/9/28 13:58.
 */
@Parcelize
data class UgcInfoBean(val id: Int, val type: String) : Parcelable