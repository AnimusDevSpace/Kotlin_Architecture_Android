package com.assignment.cleanarchitecture.ui.di

import com.assignment.cleanarchitecture.ui.ui.viewmodel.MainViewModel
import com.assignment.cleanarchitecture.ui.network.APIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface AppComponent {

    fun inject(viewModel: MainViewModel)
}