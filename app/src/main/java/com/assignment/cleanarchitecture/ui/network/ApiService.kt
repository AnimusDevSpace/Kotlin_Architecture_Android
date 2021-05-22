package com.assignment.cleanarchitecture.ui.network

import com.assignment.cleanarchitecture.ui.data.model.MostPopularArticles
import com.assignment.cleanarchitecture.ui.utils.const.ConstantData
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("emailed/7.json")
    suspend fun getMostPopularArticles(@Query("api-key")api:String=ConstantData.API_KEY): MostPopularArticles


}