package com.ludwiczak.myweather.database

import androidx.room.*
import com.ludwiczak.myweather.domain.Location
import io.reactivex.Single

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insert(list: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: Location)

    @Query("SELECT * FROM Locations where localizedName like :name")
    fun findLocations(name: String): Single<List<Location>>

    @Query("SELECT * FROM Locations order by localizedName ASC")
    fun getAll(): Single<List<Location>>

}