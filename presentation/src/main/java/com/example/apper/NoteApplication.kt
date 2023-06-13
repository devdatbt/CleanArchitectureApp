package com.example.apper

import android.app.Application
import com.example.apper.di.AppComponent
import com.example.apper.di.DaggerAppComponent

class NoteApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}