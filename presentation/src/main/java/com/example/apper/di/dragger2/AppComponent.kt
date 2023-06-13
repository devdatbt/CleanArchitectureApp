//package com.example.apper.di
//
//import android.app.Application
//import com.example.apper.di.module.APIModule
//import com.example.apper.di.module.AppModule
//import com.example.apper.ui.AddFragment
//import com.example.apper.ui.HomeFragment
//import com.example.apper.ui.MainActivity
//import com.example.apper.ui.UpdateFragment
//import com.example.apper.di.module.DBModule
//import com.example.apper.di.module.RepositoryModule
//import dagger.BindsInstance
//import dagger.Component
//
//@ApplicationScope
//@Component(
//    modules = [
//        DBModule::class,
//        AppModule::class,
//        APIModule::class,
//        RepositoryModule::class
//    ]
//)
//interface AppComponent {
//
//    fun inject(activity: MainActivity)
//    fun inject(fragment: HomeFragment)
//    fun inject(fragment: AddFragment)
//    fun inject(fragment: UpdateFragment)
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun build(): AppComponent
//    }
//}