package com.example.aouthentication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

const val SHARED_PREFERENCES_NAME = "com.ohad.sp"

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        SHARED_PREFERENCES = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    companion object {

        lateinit var SHARED_PREFERENCES: SharedPreferences
    }
}