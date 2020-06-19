package io.github.trumeen.view

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest


/**
 * @author  Administrator
 * @time    2020/4/19 19:33
 * @des
 *
 * @version      $
 * @updateAuthor $
 * @updateDate   $
 * @updateDes
 */
class RoundedCornerCenterCrop(val radius: Int = 0) : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
        return TransformationUtils.roundedCorners(pool, bitmap, radius)
    }
}