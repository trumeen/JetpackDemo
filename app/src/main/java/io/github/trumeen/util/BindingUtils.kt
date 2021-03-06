package io.github.trumeen.util

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.google.android.exoplayer2.SeekParameters
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.youth.banner.Banner
import io.github.trumeen.BR
import io.github.trumeen.R
import io.github.trumeen.bean.*
import io.github.trumeen.extension.*
import io.github.trumeen.ui.eyepetizer.fragment.ui.calendar.CalendarViewModel
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.ImageBannerAdapter
import io.github.trumeen.ui.base.SampleAdapter
import io.github.trumeen.ui.eyepetizer.ugc.UgcListViewModel
import io.github.trumeen.view.GridSpacingItemDecoration
import io.github.trumeen.weight.AutoPlayVideoPlayer
import io.github.trumeen.weight.CalendarView
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager
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

    @BindingAdapter("app:imgGlide")
    @JvmStatic
    fun setImgGlide(
        imageView: ImageView,
        url: String?
    ) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        imageView.loadGlide(url!!)
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
        }

    }

    @BindingAdapter("app:gotoPage")
    @JvmStatic
    fun goToPage(view: View, url: String?) {
        view.setOnClickListener {
            if (!url.isNullOrEmpty()) {
                view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
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

    @BindingAdapter("app:srcLoacal")
    @JvmStatic
    fun setLocalSrc(view: ImageView, dates: List<Any>) {
        if (dates.size > 1) {
            view.visible(true)
            view.setImageResource(R.drawable.muti_pic)
        } else {
            view.visible(false)
        }
    }

    @BindingAdapter("app:bindVideoPlayUrl", "app:bindVideoCover")
    @JvmStatic
    fun setVideoInfo(video: AutoPlayVideoPlayer, playUrl: String, coverUrl: String) {
        val imageView = ImageView(video.context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.load(coverUrl)
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption.setThumbImageView(imageView)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(playUrl)
            .setVideoTitle("")
            .setCacheWithPlay(false)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any?) {
                    super.onPrepared(url, *objects)
                    if (video.gsyVideoManager.player is Exo2PlayerManager) {
                        (video.gsyVideoManager
                            .player as Exo2PlayerManager).setSeekParameter(SeekParameters.NEXT_SYNC)
                    }
                }
            }).build(video)

    }

    @BindingAdapter("app:videoVideoPlayUrl")
    @JvmStatic
    fun bindVideoView(videoView: VideoView, url: String) {
        videoView.setVideoPath(url)
        videoView.start()
    }

    @BindingAdapter("app:bindPicListAdapter", "app:bindPicItem", "app:bindPicIndicat")
    @JvmStatic
    fun bindPicListAdapter(
        viewPager: ViewPager2,
        viewModel: UgcListViewModel,
        currentData: ImageInfoBean,
        indicateTextBean: IndicateTextBean
    ) {
        if (viewPager.adapter == null || (viewPager.adapter != null && viewPager.tag != currentData.id)) {
            viewPager.tag = currentData.id
            viewPager.adapter = SampleAdapter(
                ObservableArrayList<PictureBean>().apply {
                    currentData.let {
                        addAll(it.imgs!!.map { url ->
                            PictureBean(url)
                        })
                    }
                }, R.layout.item_picture_show_layout,
                BR.image,
                BR.viewModel,
                viewModel
            )
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicateTextBean.data.postValue("${position + 1}/${currentData.imgs?.size}")
                }
            })
        }

    }

    @BindingAdapter("app:imageClick")
    @JvmStatic
    fun changeShowDesc(view: View, viewModel: UgcListViewModel) {
        view.setOnClickListener {
            viewModel.showDesc.postValue(!viewModel.showDesc.value!!)
        }
    }

}