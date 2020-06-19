package io.github.trumeen.extension

import io.github.trumeen.App
import io.github.trumeen.util.dpToPx
import io.github.trumeen.util.pxToDp


/**
 * Created by xiaojianjun on 2019-12-02.
 */
fun Float.toPx() = dpToPx(App.instance, this)

fun Float.toIntPx() = dpToPx(App.instance, this).toInt()

fun Float.toDp() = pxToDp(App.instance, this)

fun Float.toIntDp() = pxToDp(App.instance, this).toInt()