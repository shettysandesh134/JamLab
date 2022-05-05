package com.sandeshshetty.jamlab.business.data.cache.abstraction

import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor

interface MedicalDaoService {

    suspend fun insertDoctorList(doctors: List<Doctor>): LongArray

}