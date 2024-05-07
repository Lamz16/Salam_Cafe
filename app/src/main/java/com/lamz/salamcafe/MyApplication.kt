package com.lamz.salamcafe

import android.app.Application
import com.lamz.salamcafe.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(dataModule)
        }
    }
}