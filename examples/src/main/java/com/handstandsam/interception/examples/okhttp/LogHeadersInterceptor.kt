package com.handstandsam.interception.examples.okhttp

import okhttp3.Interceptor


val LogHeadersInterceptor = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)
    println("--- Request Headers ---")
    println(request.headers)
    println("--- Response Headers ---")
    println(response.headers)
    response
}
