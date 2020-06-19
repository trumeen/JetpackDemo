package io.github.trumeen.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.RoundedCornersTransformation

object BindingUtils {

    @BindingAdapter("app:imgUrl", "app:radius", "app:placeholder", requireAll = false)
    @JvmStatic
    fun setImg(
        imageView: ImageView,
        url: String,
        radius: Float = 0f,
        placeholder: Drawable? = null
    ) {
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
}