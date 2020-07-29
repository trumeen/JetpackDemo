package io.github.trumeen.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import io.github.trumeen.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarView : View {

    constructor(context: Context?) : super(context) {

    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    private lateinit var mTextPaint: Paint
    private var format = SimpleDateFormat("MMMM", Locale.ENGLISH)
    private var mIconX = 0
    private var mIconY = 0
    private var mWeekStartX = 100f
    private var mWeekStartY = 300f
    private var mWeekSpace = 100f

    init {
        initPaint()
    }

    private fun initPaint() {
        mTextPaint = Paint()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mIconX = measuredWidth - 200
        mIconY = 100
        mWeekSpace = (measuredWidth - 200 - 700) / 6f
        println("measuredWidth:$measuredWidth measuredHeight:$measuredHeight mWeekSpace:$mWeekSpace")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas?) {
        drawMonthText(canvas, Calendar.getInstance().time)
        drawWeekText(canvas)
        drawIcon(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawMonthText(canvas: Canvas?, date: Date) {
        println("drawMonthText")
        mTextPaint.textSize = 100f
        mTextPaint.typeface = resources.getFont(R.font.lobster_1_4)
        mTextPaint.color = resources.getColor(R.color.colorAccent)
        canvas?.drawText(format.format(date), 100f, 200f, mTextPaint)
        canvas?.save()
    }

    private fun drawIcon(canvas: Canvas?) {
        canvas?.restore()
        canvas?.let {
            resources.getDrawable(R.drawable.icon_calendar_index).apply {
                setBounds(mIconX, mIconY, mIconX + 100, mIconY + 100)
            }.draw(it)
        }

    }

    private val mWeekTexts = arrayOf("日", "一", "二", "三", "四", "五", "六")

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawWeekText(canvas: Canvas?) {
        mTextPaint.textSize = 50f
        mTextPaint.typeface = resources.getFont(R.font.fzlantingheis_db1_gb_regular)
        mTextPaint.color = resources.getColor(R.color.colorAccent)
        canvas?.let {
            mWeekTexts.forEachIndexed { index: Int, s: String ->
                it.drawText(s, mWeekStartX + (100 + mWeekSpace) * index, mWeekStartY, mTextPaint)
            }

        }
    }

    private fun drawDateText(canvas: Canvas?) {

    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        println("onScrollChanged l$l t :$t oldl:$oldl oldt:$oldt")
        super.onScrollChanged(l, t, oldl, oldt)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("onTouchEvent :-->$event")
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                println("ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE -> {
                println("ACTION_MOVE")
            }
            MotionEvent.ACTION_UP -> {
                println("ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL -> {
                println("ACTION_CANCEL")
            }
        }
        return true
    }


}