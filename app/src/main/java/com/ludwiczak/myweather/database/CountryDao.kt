package com.ludwiczak.myweather.database

import androidx.room.*
import com.ludwiczak.myweather.domain.Country
import io.reactivex.Flowable

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insert(list: List<Country>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Query("SELECT * FROM Countries where parentKey=:key")
    fun findByKey(key: String): Country

    @Query("SELECT * FROM Countries")
    fun getAll(): List<Country>

    @Query("SELECT * FROM Countries")
    fun getAllFlowable(): Flowable<List<Country>>

}