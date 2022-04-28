package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Qualification
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.QualificationNetworkEntity

class QualificationNetworkMapper: EntityMapper<QualificationNetworkEntity, Qualification> {

    override fun mapFromEntity(entity: QualificationNetworkEntity): Qualification {
        return Qualification(
            id = entity.id,
            did = entity.did,
            fid = entity.fid,
            name = entity.name,
            description = entity.description,
            type = entity.type,
            status = entity.status,
            fileName = entity.fileName,
        )
    }

    override fun mapToEntity(domainModel: Qualification): QualificationNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(qualifications: List<QualificationNetworkEntity>?): List<Qualification>? {
        return qualifications?.map {
            mapFromEntity(it)
        }
    }
}