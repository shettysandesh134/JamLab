package com.sandeshshetty.jamlab.di

import androidx.room.Room
import com.sandeshshetty.jamlab.BaseApplication
import com.sandeshshetty.jamlab.framework.datasource.cache.database.MedicalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductionModule {

    @Singleton
    @Provides
    fun provideMedicalDb(application: BaseApplication): MedicalDatabase {
        return Room
            .databaseBuilder(application, MedicalDatabase::class.java, MedicalDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}