package com.hfad.marketsuperapplication

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)}
    }
}