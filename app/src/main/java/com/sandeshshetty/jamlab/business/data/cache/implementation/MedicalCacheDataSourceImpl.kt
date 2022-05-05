package com.sandeshshetty.jamlab.business.data.cache.implementation

import com.sandeshshetty.jamlab.business.data.cache.abstraction.MedicalCacheDataSource
import com.sandeshshetty.jamlab.business.data.cache.abstraction.MedicalDaoService
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalCacheDataSourceImpl
@Inject
constructor(
    private val medicalDaoService: MedicalDaoService
): MedicalCacheDataSource {

    override suspend fun insertDoctorList(doctors: List<Doctor>): LongArray {
        return medicalDaoService.insertDoctorList(doctors)
    }

}