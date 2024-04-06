package com.example.apper.di

import android.app.Application
import com.example.apper.MainActivity
import com.example.apper.di.module.*
import com.example.apper.ui.*
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DBModule::class,
        AppModule::class,
        APIModule::class,
        RepositoryModule::class,
        FirebaseModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: AddFragment)
    fun inject(fragment: UpdateFragment)
    fun inject(fragment: LoginFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}