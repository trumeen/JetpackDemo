package io.github.trumeen.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.trumeen.R
import io.github.trumeen.extension.showToast
import io.github.trumeen.weight.CalendarView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar_view.setOnDateSelectedListener(object :CalendarView.DateSelectedListener{
            override fun onSelected(date: Date) {

                showToast("选择的日期为：${date.time}")
            }

        })
    }

}
