package com.cleanarchitecture.network

import com.google.gson.annotations.SerializedName

/**
 * Created by sumanabhi
 * on 10,Jun,2021
 * at 17:18
 **/
open class BaseResponse {

    @field:SerializedName("apiVersion")
    var apiVersion: String? = null

    @field:SerializedName("status")
    var status: Boolean? = false

    @field:SerializedName("data")
    var data: String? = null

    @field:SerializedName("message")
    var message: String? = null
}
