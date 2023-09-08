package com.handstandsam.interception.examples.okhttp

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

private val cache = mutableMapOf<HttpUrl, String?>()
private fun buildResponseWithBodyStr(request: Request, bodyStr: String?): Response {
    return Response.Builder()
        .code(200)
        .protocol(Protocol.HTTP_2)
        .request(request)
        .message("")
        .body(bodyStr?.toResponseBody())
        .build()
}


val CachingInterceptor = Interceptor { chain ->
    val request = chain.request()
    val bodyStr = cache[request.url] ?: run {
        val response = chain.proceed(request)
        response.body?.string().also {
            cache[request.url] = it
        }
    }
    buildResponseWithBodyStr(request, bodyStr)
}

