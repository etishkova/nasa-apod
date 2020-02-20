package com.example.nasaexampleapi.di.modules

import com.example.nasaexampleapi.BuildConfig
import com.example.nasaexampleapi.network.NetworkInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Provides
    @Singleton
    fun provideGson(gsonBuilder: GsonBuilder): Gson {
        gsonBuilder.setDateFormat("yyyy-MM-dd")
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideHttpClientBuilder(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(TIMEOUT_CONNECTION.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        //TODO Comment out when uncommenting below
        httpClientBuilder.addInterceptor(NetworkInterceptor())
        //TODO Uncomment to print request logs
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(interceptor)
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        @Singleton httpClient: OkHttpClient, @Singleton gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: Retrofit.Builder,
        @Singleton httpClient: OkHttpClient
    ): Retrofit {
        return retrofitBuilder.baseUrl(API_BASE_URL).client(httpClient).build()
    }

    companion object {
        const val API_BASE_URL = "https://api.nasa.gov"
        const val TIMEOUT_READ: Long = 20
        val TIMEOUT_CONNECTION = if (BuildConfig.DEBUG) 60 else 15
    }
}