package com.example.apper.di.module

import com.example.apper.di.ApplicationScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module
object FirebaseModule {
    @Provides
    @ApplicationScope
    fun auth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @ApplicationScope
    fun firestore(): FirebaseFirestore = Firebase.firestore
}