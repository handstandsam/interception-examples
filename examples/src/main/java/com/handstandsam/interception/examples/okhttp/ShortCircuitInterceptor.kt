package com.handstandsam.interception.examples.okhttp

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

val ShortCircuitInterceptor = Interceptor { chain ->
    val request = chain.request()
    Response.Builder()
        .code(200)
        .protocol(Protocol.HTTP_2)
        .request(request)
        .message("")
        .body("Short Circuited".toResponseBody())
        .build()
}
