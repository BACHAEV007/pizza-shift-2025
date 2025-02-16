package com.example.pizza_shift_2025

import android.app.Application
import android.content.Context
import com.example.pizza_shift_2025.di.module
import org.koin.core.context.startKoin

class AppContext : Application() {

    companion object {
        lateinit var instance: AppContext
            private set

        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin{
            modules(
                networkModule,
                module
            )
        }
    }
}