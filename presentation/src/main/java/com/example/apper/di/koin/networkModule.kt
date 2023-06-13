package com.example.apper.di.koin

import com.example.data.datasource.remote.CurrencyApiService
import com.example.data.utils.BASE_URL_CURRENCY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideMoshiFactory() }
    single { provideHttpClient() }
    single { provideRetrofitBuilder(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

fun provideMoshiFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
}

fun provideHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .build()

fun provideRetrofit(
    retrofitBuilder: Retrofit.Builder
): Retrofit = retrofitBuilder.baseUrl(BASE_URL_CURRENCY).build()

fun provideRetrofitBuilder(
    okHttpClient: OkHttpClient,
    converterFactory: MoshiConverterFactory
): Retrofit.Builder = Retrofit.Builder()
    .client(okHttpClient)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(converterFactory)

fun provideApi(retrofit: Retrofit): CurrencyApiService {
    return retrofit.create(CurrencyApiService::class.java)
}