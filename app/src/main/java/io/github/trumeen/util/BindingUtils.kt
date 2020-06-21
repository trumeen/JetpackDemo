package io.github.trumeen.util

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.RoundedCornersTransformation
import io.github.trumeen.extension.toMinutes
import java.lang.StringBuilder

object BindingUtils {

    @BindingAdapter("app:imgUrl", "app:radius", "app:placeholder", requireAll = false)
    @JvmStatic
    fun setImg(
        imageView: ImageView,
        url: String,
        radius: Float = 0f,
        placeholder: Drawable? = null
    ) {
        println("img url is empty :$url")
        if (TextUtils.isEmpty(url)){
            println("img url is empty")
            return
        }

        imageView.load(url) {
            placeholder?.let {
                placeholder(it)
            }
            transformations(
                RoundedCornersTransformation(
                    radius,
                    radius,
                    radius,
                    radius
                )
            )
        }
    }

    @BindingAdapter("app:minutesText")
    @JvmStatic
    fun setMinutesText(textView: TextView, seconds: Int) {
        textView.text = seconds.toMinutes()
    }

    @BindingAdapter("app:multipleText")
    @JvmStatic
    fun setMultipleText(textView: TextView,strings:List<String>){
        val builder = StringBuilder()
        strings.forEachIndexed { index, s ->
            builder.append(s)
            if(index!=strings.size-1){
                builder.append("\n")
                builder.append("\n")
            }
        }
        textView.text = builder.toString()
    }
}