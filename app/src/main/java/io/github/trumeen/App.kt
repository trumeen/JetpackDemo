package io.github.trumeen

import android.app.Application
import cn.bmob.v3.Bmob
import com.blankj.utilcode.util.AppUtils
import io.github.trumeen.common.ActivityLifecycleCallbacksAdapter
import io.github.trumeen.util.core.ActivityManager
import io.github.trumeen.util.isMainProcess

/**
 * Created by xiaojianjun on 2019-07-15.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
        var versionCode: Int = 0
        var versionName = ""
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
        Bmob.initialize(this, "00eb3483ce9bafcf13186a01e5949f75");
        rigesterActivityCallbacks()
        setDayNightMode()
        versionCode = AppUtils.getAppVersionCode()
        versionName = AppUtils.getAppVersionName()
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