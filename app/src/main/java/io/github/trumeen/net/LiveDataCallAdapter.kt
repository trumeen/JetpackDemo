package io.github.trumeen.net

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.Exception
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {
    override fun adapt(call: Call<T>): LiveData<T> {
        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {//确保执行一次
                    try {
                        postValue(call.execute().body())
                    } catch (e: Exception) {
                        //postValue(ApiException(100, e.message))
                    }
                }
            }
        }
    }

    override fun responseType() = responseType
}