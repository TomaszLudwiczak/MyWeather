package com.ludwiczak.myweather.database

import androidx.room.*
import com.ludwiczak.myweather.domain.AdministrativeArea
import io.reactivex.Flowable

@Dao
interface AdministrativeAreaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insert(list: List<AdministrativeArea>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(area: AdministrativeArea)

    @Query("SELECT * FROM AdministrativeAreas where parentKey=:key")
    fun findByKey(key: String): AdministrativeArea

    @Query("SELECT * FROM AdministrativeAreas")
    fun getAll(): List<AdministrativeArea>

    @Query("SELECT * FROM AdministrativeAreas")
    fun getAllFlowable(): Flowable<List<AdministrativeArea>>

}