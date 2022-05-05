package com.sandeshshetty.jamlab.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor

@Dao
interface MedicalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctors(doctors: List<Doctor>): LongArray

}