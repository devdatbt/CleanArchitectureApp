package com.example.apper.di.koin

import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CurrencyRepositoryImpl(get())
    }
    single {
        NoteRepositoryImpl(get())
    }
}