package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.SpecialityNetworkEntity

class SpecialityNetworkMapper : EntityMapper<SpecialityNetworkEntity, Speciality> {

    override fun mapFromEntity(entity: SpecialityNetworkEntity): Speciality {
        entity?.let {
            return Speciality(
                id = entity.id,
                did = entity.did,
                name = entity.name,
                description = entity.description,
                type = entity.type,
                status = entity.status
            )
        }
    }

    override fun mapToEntity(domainModel: Speciality): SpecialityNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(entities: List<SpecialityNetworkEntity>?): List<Speciality>? {
        entities?.let {
            return entities?.map { entity ->
                mapFromEntity(entity)
            }
        }
        return null
    }

}