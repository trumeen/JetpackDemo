package io.github.trumeen.ui.eyepetizer.ugc

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.trumeen.bean.DataX
import io.github.trumeen.bean.ImageInfoBean
import io.github.trumeen.ui.base.BaseViewModel

class UgcListViewModel : BaseViewModel() {

    val mList: ObservableArrayList<ImageInfoBean> = ObservableArrayList()
    var currentDataX: MutableLiveData<ImageInfoBean> = MutableLiveData()
    var picIndexText: MutableLiveData<String> = MutableLiveData()

}
