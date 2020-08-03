package com.ludwiczak.myweather.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ludwiczak.myweather.BuildConfig
import com.ludwiczak.myweather.network.OkHttpInterceptor
import com.ludwiczak.myweather.network.WeatherApi
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideInterceptor(): OkHttpInterceptor = OkHttpInterceptor()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideOkhttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor, okHttpInterceptor: OkHttpInterceptor): OkHttpClient.Builder {

        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)
        client.followRedirects(true)
        client.followSslRedirects(true)
        client.cache(cache)
        client.addNetworkInterceptor(okHttpInterceptor)
        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(httpLoggingInterceptor)
        }
        client.addNetworkInterceptor(StethoInterceptor())

        return client
    }

    @Provides
    fun provideBaseOkhttpClient(client: OkHttpClient.Builder): OkHttpClient = client.build()

    @Provides
    fun provideCache(): Cache = Cache(File(System.getProperty("java.io.tmpdir"), "okhttp-cache"), 10L * 1024 * 1024)


    @Provides
    fun retrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    fun provideApiRetrofit(client: OkHttpClient, builder: Retrofit.Builder): WeatherApi {
        return builder
                .client(client)
                .baseUrl(BuildConfig.AccuWeatherHost)
                .build()
                .create(WeatherApi::class.java)
    }


    @Provides
    @Singleton
    fun providePicasso(@ApplicationContext context: Context, client: OkHttpClient) = Picasso.Builder(context)
        .downloader( OkHttp3Downloader(client))
        .listener{_, _, exception ->
            exception.printStackTrace()
        }
        .build();


}