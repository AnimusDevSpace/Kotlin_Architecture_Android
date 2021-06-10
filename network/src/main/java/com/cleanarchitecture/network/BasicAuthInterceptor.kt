package com.cleanarchitecture.network

import okhttp3.Credentials.basic
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by sumanabhi
 * on 10,Jun,2021
 * at 17:03
 **/
class BasicAuthInterceptor(user: String, password: String) : Interceptor {
    private val credentials: String = basic(username = user, password = password)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val authenticatedRequest = request.newBuilder()
            .header("Authorizations", credentials).build()

        return chain.proceed(authenticatedRequest)
    }
}
