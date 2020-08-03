package com.ludwiczak.myweather.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Locations",
    indices = [
        Index(value = ["key"], unique = true)
    ]
)
data class Location(
    @PrimaryKey
    @SerializedName("Key") var key: String,
    @SerializedName("Type") var type: String?,
    @SerializedName("Rank") var rank: Int?,
    @SerializedName("LocalizedName") var localizedName: String?,
    @SerializedName("Version") var version: Int?
) {
    @SerializedName("Country") @Ignore var country: Country? = null
    @SerializedName("AdministrativeArea") @Ignore var administrativeArea: AdministrativeArea? = null
}
