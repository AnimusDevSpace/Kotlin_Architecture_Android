package com.cleanarchitecture.network

import java.lang.Exception

/**
 * A generic class that holds a value with it's loading status
 *
 */

/**
 * Created by sumanabhi
 * on 10,Jun,2021
 * at 17:21
 **/

sealed class ApiResult<out R> {
    data class Success<out T>(val data:T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}
