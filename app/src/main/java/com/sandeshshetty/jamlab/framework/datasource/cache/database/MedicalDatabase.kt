package com.sandeshshetty.jamlab.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [], version = 1, exportSchema = false)
abstract class MedicalDatabase: RoomDatabase() {

    abstract fun medicalDao(): MedicalDao

    companion object {
        const val DATABASE_NAME = "medical_db"
    }

}