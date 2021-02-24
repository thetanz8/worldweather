package com.test.worldwether

import android.app.Application
import com.test.worldwether.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@MainApplication)
//            androidLogger()
//            modules(appModule)
//        }
    }
}