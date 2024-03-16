package com.jadhavrupesh.composequote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuotesApp  : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}