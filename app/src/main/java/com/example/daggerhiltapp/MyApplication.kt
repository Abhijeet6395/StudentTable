package com.example.daggerhiltapp


import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//Dependency Injection Setup
@HiltAndroidApp
class MyApplication : Application()
