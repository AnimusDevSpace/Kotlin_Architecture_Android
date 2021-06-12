package com.assignment.cleanarchitecture.ui.di

import android.content.Context
import com.cleanarchitecture.network.services.APIServices
import com.cleanarchitecture.network.services.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ashutosh kumar
* */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesContext(@ApplicationContext app:Context): Context = app.applicationContext


    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext context: Context): APIServices {
        return ApiClient.getApiService()
    }
}