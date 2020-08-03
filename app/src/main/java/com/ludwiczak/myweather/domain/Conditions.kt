package com.ludwiczak.myweather.domain

import com.google.gson.annotations.SerializedName

data class Conditions(
    @SerializedName("LocalObservationDateTime") var LocalObservationDateTime: String,
    @SerializedName("EpochTime") var EpochTime: Int?,
    @SerializedName("WeatherText") var WeatherText: String?,
    @SerializedName("WeatherIcon") var WeatherIcon: Int?,
    @SerializedName("IsDayTime") var IsDayTime: Boolean?,
    @SerializedName("Temperature") var Temperature: Metrics?,
    @SerializedName("RealFeelTemperature") var RealFeelTemperature: Metrics?,
    @SerializedName("RealFeelTemperatureShade") var RealFeelTemperatureShade: Metrics?,
    @SerializedName("RelativeHumidity") var RelativeHumidity: Float?,
    @SerializedName("IndoorRelativeHumidity") var IndoorRelativeHumidity: Float?,
    @SerializedName("DewPoint") var DewPoint: Metrics?,
    @SerializedName("Wind") var Wind: Wind?,
    @SerializedName("WindGust") var WindGust: Wind?,
    @SerializedName("UVIndex") var UVIndex: Float?,
    @SerializedName("UVIndexText") var UVIndexText: String?,
    @SerializedName("Visibility") var Visibility: Metrics?,
    @SerializedName("Pressure") var Pressure: Metrics?,
    @SerializedName("MobileLink") var MobileLink: String?,
    @SerializedName("Link") var Link: String?
)

data class Metrics(
    @SerializedName("Metric") var Metric: Values?,
    @SerializedName("Imperial") var Imperial: Values?
){
    data class Values(
        @SerializedName("Value") var Value: Float?,
        @SerializedName("Unit") var Unit: String?,
        @SerializedName("UnitType") var UnitType: Int?
    )
}

data class Wind(
    @SerializedName("Direction") var Direction: Direction?,
    @SerializedName("Speed") var Speed: Metrics?
)
data class Direction(
    @SerializedName("Degrees") var Degrees: Float?,
    @SerializedName("Localized") var Localized: String?,
    @SerializedName("English") var English: String?
)


