package com.example.proj27_subway

import android.app.Application
import com.example.proj27_subway.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SubwayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            /*androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )*/
            androidContext(this@SubwayApplication)
            modules(appModule)
        }
    }
}