package com.assignment.cleanarchitecture.ui.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

lateinit var application: Application

@Module
class AppModule(app: Application) {

    init {
        application=app
    }

    @Provides
    @Singleton
    fun providesApplication():Application= application

    @Provides
    @Singleton
    fun providesContext(): Context = application.applicationContext
}