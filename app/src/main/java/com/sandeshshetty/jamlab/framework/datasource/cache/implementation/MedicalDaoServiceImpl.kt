package com.sandeshshetty.jamlab.business.data.cache.implementation

import com.sandeshshetty.jamlab.business.data.cache.abstraction.MedicalCacheDataSource
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.framework.datasource.cache.database.MedicalDao
import com.sandeshshetty.jamlab.framework.datasource.cache.mapper.DoctorCacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalDaoServiceImpl
@Inject
constructor(
    private val medicalDao: MedicalDao,
    private val doctorCacheMapper: DoctorCacheMapper
): MedicalCacheDataSource {

    override suspend fun insertDoctorList(doctors: List<Doctor>): LongArray {
       return medicalDao.insertDoctors(doctors)
    }

}