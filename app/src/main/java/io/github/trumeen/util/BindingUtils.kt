package io.github.trumeen.util

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.BR
import com.youth.banner.Banner
import io.github.trumeen.R
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.extension.toMinutes
import io.github.trumeen.ui.eyepetizer.fragment.ui.calendar.CalendarViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.ImageBannerAdapter
import io.github.trumeen.ui.main.SampleAdapter
import io.github.trumeen.view.GridSpacingItemDecoration
import io.github.trumeen.view.RoundedCornerCenterCrop
import io.github.trumeen.weight.CalendarView
import java.util.*


object BindingUtils {

    @BindingAdapter("app:imgUrl", "app:radius", "app:placeholder", requireAll = false)
    @JvmStatic
    fun setImg(
        imageView: ImageView,
        url: String?,
        radius: Float = 0f,
        placeholder: Drawable? = null
    ) {
        if (TextUtils.isEmpty(url)) {
            println("img url is empty")
            return
        }

        imageView.load(url) {
            placeholder?.let {
                placeholder(it)
            }
            crossfade(true)
            transformations(
                RoundedCornersTransformation(
                    radius,
                    radius,
                    radius,
                    radius
                )
            )

        }
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
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
        if (banner.adapter == null) {
            val datas: ObservableArrayList<RecommendItemBean> = ObservableArrayList()
            datas.addAll(items)
            banner.setBannerGalleryEffect(10, 5, 1f)
            banner.isAutoLoop(false)
            banner.adapter = ImageBannerAdapter(datas)
        } else {
            println("已设置adapter")
        }

    }

    @BindingAdapter("app:bindDailyGrid")
    @JvmStatic
    fun bindDailyGrid(
        recyclerView: RecyclerView,
        items: List<RecommendItemBean>
    ) {
        if (recyclerView.adapter == null) {
            val date = ObservableArrayList<RecommendItemBean>()
            date.addAll(items)
            val layoutManger = GridLayoutManager(recyclerView.context, 2)
            layoutManger.orientation = GridLayoutManager.HORIZONTAL
            recyclerView.layoutManager = layoutManger
            recyclerView.addItemDecoration(
                GridSpacingItemDecoration(
                    1,
                    10,
                    GridLayoutManager.HORIZONTAL
                )
            )
            recyclerView.adapter = SampleAdapter(
                date, R.layout.item_square_card_layout,
                BR.recommendItem
            )
        } else {
            println("已设置adapter")
        }

    }

    @BindingAdapter("app:bindCollectionGrid")
    @JvmStatic
    fun bindCollectionGrid(
        recyclerView: RecyclerView,
        items: List<RecommendItemBean>
    ) {
        if (recyclerView.adapter == null) {
            val date = ObservableArrayList<RecommendItemBean>()
            date.addAll(items)
            val layoutManger = GridLayoutManager(recyclerView.context, 2)
            layoutManger.orientation = GridLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManger
            recyclerView.addItemDecoration(GridSpacingItemDecoration(1, 10))
            recyclerView.adapter = SampleAdapter(
                date, R.layout.item_collection_card_layout,
                BR.recommendItem
            )
        } else {
            println("已设置adapter")
        }

    }

    @BindingAdapter("app:gotoPage")
    @JvmStatic
    fun goToPage(view: View, url: String?) {
        if (url.isNullOrEmpty()) {
            return
        }
        view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    @BindingAdapter("layout_constraintDimensionRatio")
    @JvmStatic
    fun setConstraintDimensionRatio(view: View, ratio: String) {
        var setRatio = ratio
        val map = ratio.split(":").map {
            it.toInt()
        }
        if (map[0] / map[1].toFloat() >= 1.8) {
            setRatio = "1:1"
        }
        val params =
            view.layoutParams as ConstraintLayout.LayoutParams
        params.dimensionRatio = setRatio
        view.layoutParams = params
    }

    @BindingAdapter("app:onSelectedDateChange")
    @JvmStatic
    fun setOnSelectedDateChange(view: CalendarView, viewModel: CalendarViewModel) {
        view.setOnDateSelectedListener(object : CalendarView.DateSelectedListener {
            override fun onSelected(date: Date) {
                viewModel.refreshData(date)
            }

        })

    }

}