package com.cleanarchitecture.network.services

import android.content.Context
import com.cleanarchitecture.network.BaseApiClient
import com.cleanarchitecture.network.utils.const.ConstantData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient constructor(
    context: Context
) : BaseApiClient(context) {

    companion object{
        fun getApiService(): APIServices {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(ConstantData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIServices::class.java)
        }
    }

//    val apiServices: APIServices by lazy {
//        Retrofit.Builder()
//            .client(createOkHttpClient(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)))
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(APIServices::class.java)
//    }


}
