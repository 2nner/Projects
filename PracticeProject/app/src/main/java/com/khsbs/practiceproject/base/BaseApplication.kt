package com.khsbs.practiceproject.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var appContext : Context
        const val TAG = "PracticeProject"
    }
}