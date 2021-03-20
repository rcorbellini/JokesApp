package com.corbellini.jokes.core.initilizer


import android.content.Context
import androidx.startup.Initializer
import com.corbellini.jokes.BuildConfig
import timber.log.Timber

/** Config timber on init app */
class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) return
        Timber.plant(Timber.DebugTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}