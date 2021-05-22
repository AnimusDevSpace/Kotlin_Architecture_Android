package com.assignment.cleanarchitecture.ui.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.assignment.cleanarchitecture.R
import com.assignment.cleanarchitecture.ui.utils.Response
import com.assignment.cleanarchitecture.ui.di.MyApplication
import com.assignment.cleanarchitecture.ui.di.application
import com.assignment.cleanarchitecture.ui.network.ApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel:ViewModel(){

    @Inject
    lateinit var apiService:ApiService

    init {
        //injecting for API services
        (application as MyApplication).getAppComponent().inject(this)
    }

    fun getPostPopularArticles() = liveData(Dispatchers.IO) {
        emit(Response.loadingAPI(data = null))
        try {
            emit(Response.success(data = apiService.getMostPopularArticles()))
        } catch (exception: Exception) {
            emit(Response.failed(data = null, message = exception.message ?: application.getString(R.string.error_msg)))
        }
    }

}