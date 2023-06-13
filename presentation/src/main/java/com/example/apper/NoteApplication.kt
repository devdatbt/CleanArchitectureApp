package com.example.apper

import android.app.Application
import com.example.apper.di.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NoteApplication)
            modules(
                listOf(
                    roomModule,
                    repositoryModule,
                    networkModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}