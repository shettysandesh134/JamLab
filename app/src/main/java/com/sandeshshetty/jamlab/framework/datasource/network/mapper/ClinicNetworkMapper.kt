package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Clinic
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.ClinicNetworkEntity
import javax.inject.Inject

class ClinicNetworkMapper
@Inject
constructor(
    private val locationNetworkMapper: LocationNetworkMapper
) : EntityMapper<ClinicNetworkEntity, Clinic> {

    override fun mapFromEntity(entity: ClinicNetworkEntity): Clinic {
        return Clinic(
            clinicId = entity.clinicId,
            lid = entity.lid,
            name = entity.name,
            description = entity.description,
            type = entity.type,
            status = entity.status,
            location = entity.location?.let { locationNetworkMapper.mapFromEntity(it) }
        )
    }

    override fun mapToEntity(domainModel: Clinic): ClinicNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(clinics: List<ClinicNetworkEntity>?): List<Clinic>? {
        return clinics?.map {
            mapFromEntity(it)
        }
    }

}