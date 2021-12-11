package com.assignment.cleanarchitecture.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cleanarchitecture.network.ApiResult
import com.cleanarchitecture.network.models.MostPopularArticles
import com.cleanarchitecture.network.services.APIServices
import com.cleanarchitecture.network.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var _dataResponse = MutableLiveData<ApiResult<MostPopularArticles>>()
    val dataResponse: LiveData<ApiResult<MostPopularArticles>>
        get() = _dataResponse

    @Inject
    lateinit var apiService: APIServices

//    fun getPostPopularArticles(): ApiResult<> {
//        return try {
//            viewModelScope.launch {
//                var response=apiService.getMostPopularArticles()
//
//            }
//        }catch (error:IOException){
//            ApiResult.Error(error)
//        }
//        ApiResult.Loading
//        viewModelScope.launch {
//            ApiResult.Success()
//            _dataResponse.postValue(apiService.getMostPopularArticles())
//        }

//    }


    fun getPostPopularArticles() = liveData(Dispatchers.IO) {
        emit(Response.loadingAPI(data = null))
        try {
            emit(Response.success(data = apiService.getMostPopularArticles()))
        } catch (exception: Exception) {
            emit(Response.failed(data = null, message = exception.message ?: "Error"))
        }
    }

}