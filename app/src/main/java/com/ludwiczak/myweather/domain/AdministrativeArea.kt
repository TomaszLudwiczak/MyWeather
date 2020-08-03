package com.ludwiczak.myweather.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "AdministrativeAreas"
    ,indices = [
        Index(value = ["parentKey"], unique = true)
    ]
)
data class AdministrativeArea (
    @SerializedName("ID") val ID: String,
    @SerializedName("LocalizedName") val localizedName: String
) {
    @SerializedName("parentKey")
    var parentKey: String? = null

    @PrimaryKey
    @ColumnInfo(name = "pk")
    @NonNull
    var pk: String = ""
        get() = "$ID-$parentKey"
}