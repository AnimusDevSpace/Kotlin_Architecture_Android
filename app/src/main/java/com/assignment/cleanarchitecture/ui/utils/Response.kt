package com.assignment.cleanarchitecture.ui.utils

import com.assignment.cleanarchitecture.ui.utils.Status.*


data class Response<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Response<T> = Response(status = SUCCESS, data = data, message = null)

        fun <T> failed(data: T?, message: String): Response<T> =
            Response(status = FAILED, data = data, message = message)

        fun <T> loadingAPI(data: T?): Response<T> = Response(status = LOADING_API, data = data, message = null)
    }
}