package com.assignment.cleanarchitecture.ui.network

import android.app.Application
import com.assignment.cleanarchitecture.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

lateinit var app: Application
lateinit var BASE_URL: String

@Module
class APIModule(baseUrl: String,application: Application) {

    init {
        baseUrl.also { BASE_URL = it }
        app=application
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
                     .cache(cache)
                     .addInterceptor(providesLoggingInterceptor())
        return client.build()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor():HttpLoggingInterceptor{
        val mLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY else mLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.NONE

        return mLoggingInterceptor
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }


    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache{
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesRetrofit(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(providesGson()))
            .baseUrl(BASE_URL)
            .client(providesOkHttpClient(providesOkHttpCache(app)))
            .build()
            .create(ApiService::class.java)
    }













}