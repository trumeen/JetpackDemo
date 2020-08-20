package io.github.trumeen.ui.lls

import android.os.Bundle
import io.github.trumeen.R
import io.github.trumeen.extension.loadGlide
import io.github.trumeen.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_preview.*

const val IMAGE_URL = "imageUrl"

class ImagePreviewActivity : BaseActivity() {

    override fun layoutRes() = R.layout.activity_image_preview

    override fun onStart() {
        super.onStart()
        iv_preview.loadGlide(intent.getStringExtra(IMAGE_URL))

        iv_preview.setOnClickListener {
            finish()
        }
    }

}