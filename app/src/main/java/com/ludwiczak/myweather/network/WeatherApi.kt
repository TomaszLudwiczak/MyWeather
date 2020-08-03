package com.ludwiczak.myweather.network

import com.ludwiczak.myweather.BuildConfig
import com.ludwiczak.myweather.domain.Conditions
import com.ludwiczak.myweather.domain.Location
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("locations/v1/cities/autocomplete")
    fun getCitiesByQuery(
        @Query("apikey") apikey: String = BuildConfig.AccuWeatherApiKey,
        @Query("q") q: String = "",
        @Query("language") language: String = "en-us")
            : Single<List<Location>>


    @GET("/currentconditions/v1/{key}")
    fun getCurrentConditions(
        @Path("key") key: String,
        @Query("apikey") apikey: String = BuildConfig.AccuWeatherApiKey,
        @Query("details") details: Boolean = true,
        @Query("language") language: String = "en-us"
    ): Single<List<Conditions>>
}