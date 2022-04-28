package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Symptom
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.SymptomNetworkEntity

class SymptomNetworkMapper: EntityMapper<SymptomNetworkEntity, Symptom> {

    override fun mapFromEntity(entity: SymptomNetworkEntity): Symptom {
        return Symptom(
            id = entity.id,
        did = entity.did,
        name = entity.name,
        description = entity.description,
        type = entity.type,
        status = entity.status
        )
    }

    override fun mapToEntity(domainModel: Symptom): SymptomNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(symptoms: List<SymptomNetworkEntity>?): List<Symptom>? {
        return symptoms?.map {
            mapFromEntity(it)
        }
    }

}