package com.galanz.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import java.util.*

class Person(var name: String, var age: Int, var imgUrl: String) {

    val liveName = MutableLiveData<String>()
    val liveAge = MutableLiveData<Int>()
    val liveImg = MutableLiveData<String>()

    init {
        liveName.value = name
        liveAge.value = age
        liveImg.value = imgUrl
    }

    fun updateName() {
        println("updateName")
        liveName.value = UUID.randomUUID().toString()
    }

}