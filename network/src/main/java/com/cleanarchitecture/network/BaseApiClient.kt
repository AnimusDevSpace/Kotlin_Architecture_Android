package com.cleanarchitecture.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by sumanabhi
 * on 07,Jun,2021
 * at 19:08
 **/
abstract class BaseApiClient constructor(context: Context) {

    companion object {
        private const val CACHE_SIZE = 10 * 1024 * 1024
        private const val TIME_OUT = 30L
    }

    /**
    * Create and return a new OkHttpClient
    *
    * @param interceptor the interceptor to intercept the api request. default is null
    */
    fun createOkHttpClient(interceptor: Interceptor? = null): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
//        httpClientBuilder.cache()
        httpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS)

        interceptor?.let { httpClientBuilder.addInterceptor(interceptor) }

        //when we need to append authentication in the API
//        authInterceptor?.let { httpClientBuilder.addInterceptor(authInterceptor) }

        return httpClientBuilder.build()
    }
}
