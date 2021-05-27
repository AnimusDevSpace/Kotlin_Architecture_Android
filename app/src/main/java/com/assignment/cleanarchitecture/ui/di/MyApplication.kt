package com.assignment.cleanarchitecture.ui.di

import android.app.Application
import com.assignment.cleanarchitecture.ui.network.APIModule
import com.assignment.cleanarchitecture.ui.utils.const.ConstantData
import dagger.hilt.android.HiltAndroidApp

class MyApplication:Application() {

    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
              appComponent=DaggerAppComponent.builder()
                 .aPIModule(APIModule(ConstantData.BASE_URL,this))
                 .appModule(AppModule(this))
                 .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}