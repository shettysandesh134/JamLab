package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.consultation.Clinic
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.business.domain.model.user.Location
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.DoctorNetworkEntity
import javax.inject.Inject

class DoctorNetworkMapper
@Inject
constructor(
    private val locationNetworkMapper: LocationNetworkMapper,
    private val clinicNetworkMapper: ClinicNetworkMapper,
    private val specialityNetworkMapper: SpecialityNetworkMapper,
    private val symptomNetworkMapper: SymptomNetworkMapper,
    private val qualificationNetworkMapper: QualificationNetworkMapper
) : EntityMapper<DoctorNetworkEntity, Doctor> {

    override fun mapFromEntity(entity: DoctorNetworkEntity): Doctor {
        return Doctor(
            id = entity.id,
            lid = entity.lid,
            fname = entity.fname,
            lname = entity.lname,
            description = entity.description,
            doctortype = entity.doctortype,
            website = entity.website,
            gender = entity.gender,
            dob = entity.dob,
            email = entity.email,
            phone = entity.phone,
            practionerRegistrationNumber = entity.practionerRegistrationNumber,
            experience = entity.experience,
            language = entity.language,
            timezone = entity.timezone,
            digitalIdStatus = entity.digitalIdStatus,
            policeCheckStatus = entity.policeCheckStatus,
            price = entity.price,
            rating = entity.rating,
            channel = entity.channel,
            type = entity.type,
            status = entity.status,
            relevance = entity.relevance,
            location = entity.location?.let {
                locationNetworkMapper?.mapFromEntity(it)
            },
            clinics = entity.clinics?.let {
                clinicNetworkMapper?.mapFromEntityList(it)
            },
            specialities = entity.specialities?.let {
                specialityNetworkMapper?.mapFromEntityList(it)
            },
            symptoms = entity.symptoms?.let {
                symptomNetworkMapper?.mapFromEntityList(it)
            },
            qualifications = entity.qualifications?.let {
                qualificationNetworkMapper?.mapFromEntityList(it)
            }
        )
    }

    override fun mapToEntity(domainModel: Doctor): DoctorNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(doctors: List<DoctorNetworkEntity>?): List<Doctor>? {
        return doctors?.map {
            mapFromEntity(it)
        }
    }

}