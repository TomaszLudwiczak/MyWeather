package com.ludwiczak.myweather.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Countries"
    ,indices = [
        Index(value = ["parentKey"], unique = true)
    ]
)
data class Country (
    @SerializedName("ID") var ID: String,
    @SerializedName("LocalizedName") var localizedName: String
){
    @SerializedName("parentKey") var parentKey: String? = null

    @PrimaryKey
    @ColumnInfo(name = "pk")
    @NonNull
    var pk: String = ""
        get() = "$ID-$parentKey"
}