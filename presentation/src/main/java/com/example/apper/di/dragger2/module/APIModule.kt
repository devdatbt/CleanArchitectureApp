//package com.example.apper.di.module
//
//import com.example.data.datasource.remote.CurrencyApiService
//import com.example.apper.di.ApplicationScope
//import com.example.data.utils.BASE_URL_CURRENCY
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//@Module
//object APIModule {
//    @Provides
//    @ApplicationScope
//    fun provideRetrofit(
//        retrofitBuilder: Retrofit.Builder
//    ): Retrofit = retrofitBuilder.baseUrl(BASE_URL_CURRENCY).build()
//
//    @Provides
//    @ApplicationScope
//    fun provideRetrofitBuilder(
//        okHttpClient: OkHttpClient,
//        converterFactory: MoshiConverterFactory
//    ): Retrofit.Builder = Retrofit.Builder()
//        .client(okHttpClient)
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//        .addConverterFactory(converterFactory)
//
//    @Provides
//    @ApplicationScope
//    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
//        .connectTimeout(60, TimeUnit.SECONDS)
//        .readTimeout(60, TimeUnit.SECONDS)
//        .writeTimeout(60, TimeUnit.SECONDS)
//        .build()
//
//    @Provides
//    @ApplicationScope
//    fun provideMoshiConverter(): MoshiConverterFactory =
//        MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
//
//    @Provides
//    @ApplicationScope
//    fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService =
//        retrofit.create(CurrencyApiService::class.java)
//}