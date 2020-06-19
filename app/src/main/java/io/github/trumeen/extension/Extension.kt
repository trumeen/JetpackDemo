package io.github.trumeen.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.github.trumeen.view.RoundedCornerCenterCrop


/**
 * @author  Administrator
 * @time    2020/4/11 20:30
 * @des
 *
 * @version      $
 * @updateAuthor $
 * @updateDate   $
 * @updateDes
 */


fun ImageView.load(url: String) =
    Glide.with(this.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)


fun ImageView.load(url: String, radius: Int) =
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(
            RequestOptions.bitmapTransform(
                RoundedCornerCenterCrop(
                    radius
                )
            )
        )
        .into(this)