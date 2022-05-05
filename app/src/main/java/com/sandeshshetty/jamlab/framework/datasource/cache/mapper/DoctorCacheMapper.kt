package com.sandeshshetty.jamlab.framework.datasource.cache.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.cache.model.DoctorCacheEntity

class DoctorCacheMapper: EntityMapper<DoctorCacheEntity, Doctor> {

    override fun mapFromEntity(entity: DoctorCacheEntity): Doctor {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Doctor): DoctorCacheEntity {
        TODO("Not yet implemented")
    }

    fun mapToEntityList(doctors: List<Doctor>): List<DoctorCacheEntity> {
        return doctors.map {
            mapToEntity(it)
        }
    }

}