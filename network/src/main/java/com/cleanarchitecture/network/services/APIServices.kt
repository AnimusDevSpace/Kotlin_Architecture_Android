package com.cleanarchitecture.network.services


import com.cleanarchitecture.network.models.MostPopularArticles
import com.cleanarchitecture.network.utils.const.ConstantData
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {


    @GET("emailed/7.json")
    suspend fun getMostPopularArticles(@Query("api-key") api: String = ConstantData.API_KEY): MostPopularArticles


}
