package io.github.trumeen.ui.eyepetizer

import androidx.lifecycle.MutableLiveData
import io.github.trumeen.ui.base.BaseViewModel

class EyepettizerViewModel : BaseViewModel() {

    var mBottomButtonState: MutableLiveData<Boolean> = MutableLiveData(true)

}