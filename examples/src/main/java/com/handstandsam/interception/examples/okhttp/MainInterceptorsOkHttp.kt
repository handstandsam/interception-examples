package com.handstandsam.interception.examples.okhttp

import ITEMS_URL
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * https://square.github.io/okhttp/features/interceptors/
 */
object MainInterceptorsOkHttp {

    @JvmStatic
    fun main(args: Array<String>) {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        makeGetCall(okHttpClient, ITEMS_URL)
    }











    private fun makeGetCall(okHttpClient: OkHttpClient, url: String) {
        val request: Request = Request.Builder().url(url).build()
        val call: Call = okHttpClient.newCall(request)
        val response = call.execute()
        val responseText = response.body?.string()
        println("------$url-------")
        println(responseText)
    }
}