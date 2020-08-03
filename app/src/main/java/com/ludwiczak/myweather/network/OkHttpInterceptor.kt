package com.ludwiczak.myweather.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


open class OkHttpInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val b: Request.Builder = chain.request().newBuilder()
        b.addHeader("Accept-Encoding", "gzip");
        b.addHeader("Accept-Encoding", "gzip");
        return chain.proceed(b.build())
    }
}