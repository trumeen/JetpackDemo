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

    private var mDownX: Float = 0f
    private lateinit var mChoosePaint: Paint
    private var mContext: Context? = null
    private var mCanvas: Canvas? = null
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
    private val mCurrentDay: Date

    init {
        initPaint()
        mVelocityTracker = VelocityTracker.obtain()
        mCurrentDay = mCalendar.time
    }

    private fun initPaint() {
        mTextPaint = Paint()
        mChoosePaint = Paint()
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
        drawDate(canvas)
        drawMonthText(canvas)
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
        mTextPaint.textAlign = Paint.Align.LEFT
        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum)
        mCalendar.set(Calendar.DAY_OF_WEEK, mCalendar.firstDayOfWeek)
        val date = mCalendar.time
        mTextPaint.textSize = 100f
        mTextPaint.typeface = resources.getFont(R.font.lobster_1_4)
        mTextPaint.color = resources.getColor(R.color.colorAccent)
        canvas?.drawText(format.format(date), mPaddingStart - 50, 200f, mTextPaint)
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
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.textSize = 50f
        mTextPaint.typeface = resources.getFont(R.font.fzlantingheis_db1_gb_regular)
        mTextPaint.color = resources.getColor(R.color.text_black)
        canvas?.let {
            mWeekTexts.forEachIndexed { index: Int, s: String ->
                it.drawText(s, mLabelStartX + (100 + mWeekSpace) * index, mWeekStartY, mTextPaint)
            }
        }
    }

    private var mWeekNum = mCalendar.get(Calendar.WEEK_OF_YEAR)

    private fun drawDate(canvas: Canvas?) {
        var weeksInWeekYear = mCalendar.firstDayOfWeek
        mTextPaint.textSize = 60f
        mTextPaint.typeface = resources.getFont(R.font.fzlantingheis_db1_gb_regular)
        mTextPaint.color = resources.getColor(R.color.text_black)
        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum)
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + index)
            canvas?.let {
                val x = mWeekStartX + (mPaddingStart + mWeekSpace) * index
                val y = mWeekStartY + 100
                drawDataText(it, x, y)
            }
        }

        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum - 1)
        weeksInWeekYear = mCalendar.firstDayOfWeek
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + (6 - index))
            canvas?.let {
                val x = mWeekStartX - 200 - (mPaddingStart + mWeekSpace) * index - mPaddingEnd
                val y = mWeekStartY + 100
                drawDataText(it, x, y)
            }
        }

        mCalendar.set(Calendar.WEEK_OF_YEAR, mWeekNum + 1)
        weeksInWeekYear = mCalendar.firstDayOfWeek
        (0..6).forEach { index ->
            mCalendar.set(Calendar.DAY_OF_WEEK, weeksInWeekYear + index)
            canvas?.let {
                val x = mWeekStartX + mWidth + (mPaddingStart + mWeekSpace) * index
                val y = mWeekStartY + 100
                drawDataText(it, x, y)
            }
        }
    }

    private fun drawDataText(canvas: Canvas, x: Float, y: Float) {
        canvas.run {
            mChoosePaint.color = resources.getColor(R.color.Yellow)
            if (isTheSameDay(mCurrentDay, mCalendar.time)) {
                drawCircle(x, y - 30, 60f, mChoosePaint)
            }
            mTextPaint.textAlign = Paint.Align.CENTER
            drawText(
                mCalendar.get(Calendar.DAY_OF_MONTH).toString(),
                x,
                y - 8,
                mTextPaint
            )
        }
    }

    private var mStartScrollX = 0f
    private var mStartScrollY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("event-->$event")
        mVelocityTracker.addMovement(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartScrollX = event.x
                mDownX = event.x
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
                val fl = (event.x - mDownX)
                if (abs(fl) <= ViewConfiguration.get(mContext).scaledTouchSlop) {
                    //点击事件
                    println("点击事件")
                } else {
                    mVelocityTracker.computeCurrentVelocity(
                        1000,
                        mConfiguration.scaledMaximumFlingVelocity.toFloat()
                    )
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
                                    if (delet == 0) {
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
                                    if (delet == 0) {
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

    private fun isTheSameDay(day1: Date, day2: Date): Boolean {
        val calendar1 = Calendar.getInstance()
        calendar1.time = day1
        val calendar2 = Calendar.getInstance()
        calendar2.time = day2
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)

    }


}