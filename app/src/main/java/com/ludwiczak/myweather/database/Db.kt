package com.ludwiczak.myweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ludwiczak.myweather.domain.AdministrativeArea
import com.ludwiczak.myweather.domain.Country
import com.ludwiczak.myweather.domain.Location

@Database(
        entities = [
            Location::class,
            Country::class,
            AdministrativeArea::class
        ],
        version = 1
)

abstract class Db : RoomDatabase() {
    companion object {
        private const val DB_NAME = "weather.db"

        @Volatile
        private var instance: Db? = null


        fun getInstance(context: Context): Db {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room
                                .databaseBuilder(context, Db::class.java, DB_NAME)
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }

            return instance!!
        }
    }


    abstract fun locationDao(): LocationDao
    abstract fun countryDao(): CountryDao
    abstract fun administrativeAreaDao(): AdministrativeAreaDao


}