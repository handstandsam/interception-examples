package com.handstandsam.interception.examples.okhttp

import okhttp3.Interceptor

val PassthroughInterceptor = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)
    response
}
