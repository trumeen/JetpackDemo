package io.github.trumeen.weight

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.contains
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.Banner
import io.github.trumeen.bean.RecommendItemBean
import io.github.trumeen.ui.eyepetizer.fragment.ui.home.ImageBannerAdapter
import kotlin.math.abs


class RecyclerViewAtViewPager2 : RecyclerView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    private var startX = 0
    private var startY = 0
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                val findChildViewUnder = findChildViewUnder(ev.x, ev.y)
                findChildViewUnder?.let {
                    if (it is ConstraintLayout) {
                        val view = it[it.childCount - 1]
                        if(view is Banner<*, *>){
                            val canScrollHorizontally = view.canScrollHorizontally(startX - endX)
                            println("findChildViewUnder banner: $canScrollHorizontally")
                            parent.requestDisallowInterceptTouchEvent(
                                canScrollHorizontally
                            )
                            view.dispatchTouchEvent(ev)
                        }else{
                            if (disX > disY) {
                                val canScrollHorizontally = view.canScrollHorizontally(
                                    startX - endX
                                )
                                println("findChildViewUnder:$view canScrollHorizontally :$canScrollHorizontally")
                                parent.requestDisallowInterceptTouchEvent(
                                    canScrollHorizontally
                                )
                            } else {
                                /*val canScrollVertically = view.canScrollVertically(
                                    startY - endY
                                )
                                parent.requestDisallowInterceptTouchEvent(
                                    canScrollVertically
                                )
                                println("findChildViewUnder:$view canScrollVertically :$canScrollVertically")*/
//                            super.dispatchTouchEvent(ev)
                            }
                        }

                    }
                }

            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(
                false
            )
        }
        return super.dispatchTouchEvent(ev)
    }
}
