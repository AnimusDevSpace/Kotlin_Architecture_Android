package com.assignment.cleanarchitecture.ui.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.assignment.cleanarchitecture.R
import com.assignment.cleanarchitecture.ui.utils.Response
import com.assignment.cleanarchitecture.ui.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor ():ViewModel(){

    @Inject
    lateinit var apiService:ApiService


    fun getPostPopularArticles() = liveData(Dispatchers.IO) {
        emit(Response.loadingAPI(data = null))
        try {
            emit(Response.success(data = apiService.getMostPopularArticles()))
        } catch (exception: Exception) {
            emit(Response.failed(data = null, message = exception.message ?: "Error"))
        }
    }

}