package io.github.trumeen

import android.app.Application
import io.github.trumeen.common.ActivityLifecycleCallbacksAdapter
import io.github.trumeen.util.core.ActivityManager
import io.github.trumeen.util.isMainProcess

/**
 * Created by xiaojianjun on 2019-07-15.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // 主进程初始化
        if (isMainProcess(this)) {
            init()
        }
    }

    private fun init() {
        rigesterActivityCallbacks()
        setDayNightMode()
    }

    private fun setDayNightMode() {
        //setNightMode(SettingsStore.getNightMode())
    }

    private fun rigesterActivityCallbacks() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksAdapter(
            onActivityCreated = { activity, _ ->
                ActivityManager.activities.add(activity)
            },
            onActivityDestroyed = { activity ->
                ActivityManager.activities.remove(activity)
            }
        ))
    }
}