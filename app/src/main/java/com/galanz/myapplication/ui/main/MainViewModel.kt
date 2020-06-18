package com.galanz.myapplication.ui.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val mListDatas = MutableLiveData<ObservableArrayList<Person>>()

    fun addData(person: Person) {
        println("add data:${person.liveName.value}")
        mListDatas.value?.add(person)
    }


}