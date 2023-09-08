package com.handstandsam.interception.examples.okhttp

import okhttp3.Interceptor
import okhttp3.Response

val AddHeaderInterceptor = Interceptor { chain ->
    val newRequest = chain.request()
        .newBuilder()
        .addHeader("Droidcon", "NYC")
        .build()
    chain.proceed(newRequest)
}
