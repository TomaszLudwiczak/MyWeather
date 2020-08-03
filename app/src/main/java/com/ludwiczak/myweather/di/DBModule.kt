package com.ludwiczak.myweather.di

import android.content.Context
import com.ludwiczak.myweather.database.Db
import com.ludwiczak.myweather.network.WeatherApi
import com.ludwiczak.myweather.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Db.getInstance(context)


    @Provides
    fun provideRepo(db: Db, api: WeatherApi): Repository = Repository(db, api)

}