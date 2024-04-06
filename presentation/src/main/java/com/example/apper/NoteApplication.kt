package com.example.apper

import android.app.Application
import com.example.apper.di.AppComponent
import com.example.apper.di.DaggerAppComponent
import com.google.firebase.FirebaseApp

class NoteApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}