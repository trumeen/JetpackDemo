package io.github.trumeen.util

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.chad.library.BR
import com.youth.banner.Banner
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.extension.toMinutes
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.ImageBannerAdapter
import io.github.trumeen.ui.main.SampleAdapter
import io.github.trumeen.view.GridSpacingItemDecoration
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
        if (TextUtils.isEmpty(url)) {
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
    fun setMultipleText(textView: TextView, strings: List<String>) {
        val builder = StringBuilder()
        strings.forEachIndexed { index, s ->
            builder.append(s)
            if (index != strings.size - 1) {
                builder.append("\n")
                builder.append("\n")
            }
        }
        textView.text = builder.toString()
    }

    @BindingAdapter("app:bindBanner")
    @JvmStatic
    fun bindBanner(
        banner: Banner<RecommendItemBean, ImageBannerAdapter>,
        items: List<RecommendItemBean>
    ) {
        val datas: ObservableArrayList<RecommendItemBean> = ObservableArrayList()
        datas.addAll(items)
        banner.setBannerGalleryEffect(10, 5, 1f)
        banner.isAutoLoop(false)
        banner.adapter = ImageBannerAdapter(datas)

    }

    @BindingAdapter("app:bindDailyGrid")
    @JvmStatic
    fun bindDailyGrid(
        recyclerView: RecyclerView,
        items: List<RecommendItemBean>
    ) {
        val date = ObservableArrayList<RecommendItemBean>()
        date.addAll(items)
        val layoutManger = GridLayoutManager(recyclerView.context, 2)
        layoutManger.orientation = GridLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManger
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 10))
        recyclerView.adapter = SampleAdapter(
            date, R.layout.item_square_card_layout,
            BR.recommendItem
        )
    }
}