package io.github.trumeen.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller
import io.github.trumeen.R
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class CalendarView : View {

    constructor(context: Context?) : super(context) {
        context?.let {
            mScroller = Scroller(it)
        }
    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet) {
        mContext = context
        context?.let {
            mScroller = Scroller(it)
            mConfiguration = ViewConfiguration.get(it)
        }
    }

    private var mContext: Context? = null
    private var mCanvas: Canvas? = null
    private var mHasDrawIconAndLabel: Boolean = false
    private var mWidth: Int = 0
    private lateinit var mTextPaint: Paint
    private var format = SimpleDateFormat("MMMM", Locale.ENGLISH)
    private val mCalendar = Calendar.getInstance()
    private var mIconX = 0
    private var mIconY = 0
    private var mWeekStartX = 100f
    private var mLabelStartX = 100f
    private var mWeekStartY = 300f
    private var mWeekSpace = 100f
    private lateinit var mScroller: Scroller
    private val mPaddingStart = 100f
    private val mPaddingEnd = 100f
    private var mVelocityTracker: VelocityTracker
    private lateinit var mConfiguration: ViewConfiguration

    init {
        initPaint()
        mVelocityTracker = VelocityTracker.obtain()
    }

    private fun initPaint() {
        mTextPaint = Paint()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mIconX = measuredWidth - 200
        mIconY = 100
        mWeekSpace = (measuredWidth - 200 - 700) / 6f
        mWidth = measuredWidth
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {

        mCanvas = canvas
        drawWeekText(canvas)
        drawIcon(canvas)
        drawDateText(canvas)
        drawMonthText(canvas)
        println("mCurrentWeekNum:${mWeekNum} mStartScrollX:${mWeekStartX} mWidth:$mWidth")
        if (mWeekStartX.toInt() == mWidth + mPaddingStart.toInt()) {
            mWeekNum--
            mWeekStartX = 100f
            invalidate()
        } else if (mWeekStartX.toInt() == -mWidth + mPaddingStart.toInt()) {
            mWeekNum++
            mWeekStartX = 100f
            invalidate()
        }
    }

    private fun drawMonthText(canvas: Canvas?) {
        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum)
        mCalendar.set(Calendar.DAY_OF_WEEK, mCalendar.firstDayOfWeek)
        val date = mCalendar.time
        mTextPaint.textSize = 100f
        mTextPaint.typeface = resources.getFont(R.font.lobster_1_4)
        mTextPaint.color = resources.getColor(R.color.colorAccent)
        canvas?.drawText(format.format(date), mPaddingStart, 200f, mTextPaint)
    }

    private fun drawIcon(canvas: Canvas?) {
        canvas?.let {
            resources.getDrawable(R.drawable.icon_calendar_index).apply {
                setBounds(mIconX, mIconY, mIconX + 100, mIconY + 100)
            }.draw(it)
        }

    }

    private val mWeekTexts = arrayOf("日", "一", "二", "三", "四", "五", "六")

    private fun drawWeekText(canvas: Canvas?) {
        mTextPaint.textSize = 50f
        mTextPaint.typeface = resources.getFont(R.font.fzlantingheis_db1_gb_regular)
        mTextPaint.color = resources.getColor(R.color.colorPrimary)
        canvas?.let {
            mWeekTexts.forEachIndexed { index: Int, s: String ->
                it.drawText(s, mLabelStartX + (100 + mWeekSpace) * index, mWeekStartY, mTextPaint)
            }
        }
    }

    private var mWeekNum = mCalendar.get(Calendar.WEEK_OF_YEAR)

    private fun drawDateText(canvas: Canvas?) {
        var weeksInWeekYear = mCalendar.firstDayOfWeek
        mTextPaint.textSize = 100f
        mTextPaint.typeface = resources.getFont(R.font.lobster_1_4)
        mTextPaint.color = resources.getColor(R.color.colorAccent)
        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum)
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + index)
            canvas?.let {
                it.drawText(
                    mCalendar.get(Calendar.DAY_OF_MONTH).toString(),
                    mWeekStartX + (mPaddingStart + mWeekSpace) * index,
                    mWeekStartY + 200,
                    mTextPaint
                )
            }
        }

        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum - 1)
        weeksInWeekYear = mCalendar.firstDayOfWeek
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + (6 - index))
            canvas?.let {
                it.drawText(
                    mCalendar.get(Calendar.DAY_OF_MONTH).toString(),
                    mWeekStartX - 200 - (mPaddingStart + mWeekSpace) * index - mPaddingEnd,
                    mWeekStartY + 200,
                    mTextPaint
                )
            }
        }

        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum + 1)
        weeksInWeekYear = mCalendar.firstDayOfWeek
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + index)
            canvas?.let {
                it.drawText(
                    mCalendar.get(Calendar.DAY_OF_MONTH).toString(),
                    mWeekStartX + mWidth + (mPaddingStart + mWeekSpace) * index,
                    mWeekStartY + 200,
                    mTextPaint
                )
            }
        }
    }

    private var mStartScrollX = 0f
    private var mStartScrollY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mVelocityTracker.addMovement(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartScrollX = event.x
                mStartScrollY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val fl = (event.x - mStartScrollX)
                if (abs(fl) > ViewConfiguration.get(mContext).scaledTouchSlop) {
                    mWeekStartX -= fl
                    mStartScrollX = event.x
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                mVelocityTracker.computeCurrentVelocity(
                    1000,
                    mConfiguration.scaledMaximumFlingVelocity.toFloat()
                )
                println("mVelocityTracker:${mVelocityTracker.xVelocity}")
                if (abs(mWeekStartX) < mWidth / 2 && abs(mVelocityTracker.xVelocity) < 2000) {
                    val start = mWeekStartX.toInt()
                    mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum)
                    mCalendar.set(Calendar.DAY_OF_WEEK, mCalendar.firstDayOfWeek)
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            if (start > mPaddingStart) {
                                (start downTo mPaddingStart.toInt() step 10).forEach {
                                    mWeekStartX = it.toFloat()
                                    delay(5)
                                    postInvalidate()
                                }
                            } else if (start < mPaddingStart.toInt()) {
                                (start..100 step 10).forEach {
                                    mWeekStartX = it.toFloat()
                                    delay(5)
                                    postInvalidate()
                                }
                            }
                            if (mWeekStartX.toInt() != mPaddingStart.toInt()) {
                                mWeekStartX = mPaddingStart
                                postInvalidate()
                            }
                        }
                    }

                } else {
                    if (mWeekStartX > 0 || mVelocityTracker.xVelocity < 0) {
                        val end = mWidth + mPaddingStart.toInt()
                        val start = mWeekStartX.toInt()
                        GlobalScope.launch {
                            withContext(Dispatchers.IO) {
                                var delet = abs(start - end) / 200
                                if(delet==0){
                                    delet = 1
                                }
                                if (start > end) {
                                    (start downTo end step delet * 10).forEach {
                                        mWeekStartX = it.toFloat()
                                        delay(10)
                                        postInvalidate()
                                    }
                                } else if (start < end) {
                                    (start..end step delet * 10).forEach {
                                        mWeekStartX = it.toFloat()
                                        delay(10)
                                        postInvalidate()
                                    }
                                }
                                if (mWeekStartX.toInt() != end) {
                                    mWeekStartX = end.toFloat()
                                    postInvalidate()
                                }
                            }
                        }

                    } else {
                        val end = -mWidth + mPaddingStart.toInt()
                        val start = mWeekStartX.toInt()
                        GlobalScope.launch {
                            withContext(Dispatchers.IO) {
                                var delet = abs(start - end) / 200
                                if(delet==0){
                                    delet = 1
                                }
                                if (start > end) {
                                    (start downTo end step delet * 10).forEach {
                                        mWeekStartX = it.toFloat()
                                        delay(10)
                                        postInvalidate()
                                    }
                                } else if (start < end) {
                                    (start..end step delet * 10).forEach {
                                        mWeekStartX = it.toFloat()
                                        delay(10)
                                        postInvalidate()
                                    }
                                }
                                if (mWeekStartX.toInt() != end) {
                                    mWeekStartX = end.toFloat()
                                    postInvalidate()
                                }
                            }
                        }
                    }

                }
            }

            MotionEvent.ACTION_CANCEL -> {
                println("ACTION_CANCEL")
            }
        }
        return true
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mVelocityTracker.recycle()
    }


}