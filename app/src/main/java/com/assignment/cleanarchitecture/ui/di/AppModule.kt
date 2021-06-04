package com.assignment.cleanarchitecture.ui.di

import android.app.Application
import android.content.Context
import androidx.databinding.library.BuildConfig
import com.assignment.cleanarchitecture.ui.network.ApiService
import com.assignment.cleanarchitecture.ui.utils.const.ConstantData.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesContext(@ApplicationContext app:Context): Context = app.applicationContext

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
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
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
    fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext context: Context): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(providesGson()))
            .baseUrl(BASE_URL)
            .client(providesOkHttpClient(providesOkHttpCache(context)))
            .build()
            .create(ApiService::class.java)
    }
}