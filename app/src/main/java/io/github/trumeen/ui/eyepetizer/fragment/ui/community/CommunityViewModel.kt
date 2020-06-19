package io.github.trumeen.ui.eyepetizer.fragment.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.trumeen.ui.base.BaseViewModel

class CommunityViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is community Fragment"
    }
    val text: LiveData<String> = _text
}