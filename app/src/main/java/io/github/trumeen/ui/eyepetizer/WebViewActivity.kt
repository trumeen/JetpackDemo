package io.github.trumeen.ui.eyepetizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import io.github.trumeen.App
import io.github.trumeen.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.File
import java.lang.StringBuilder

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        toolbar.setNavigationOnClickListener {
            if (web_view.canGoBack()) {
                web_view.goBack()
            } else {
                finish()
            }
        }
        web_view.settings?.apply {
            javaScriptEnabled = true
            builtInZoomControls = false
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)
            domStorageEnabled = true
            setAppCacheEnabled(true)
            defaultTextEncodingName = "utf-8"
            mixedContentMode = 0
            userAgentString = StringBuilder().append(userAgentString)
                .append("Eyepetizer")
                .append(File.separator)
                .append("${App.versionName}.${App.versionCode}")
                .toString()
        }

        intent.data?.apply {
            if (query != null) {
                val title = getQueryParameter("title")
                val url = getQueryParameter("url")
                if (!url.isNullOrEmpty()) {
                    println("url:$url")
                    web_view.loadUrl(url)
                }
            }
        }

    }
}