package com.example.nasaexampleapi.network

import com.example.nasaexampleapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}