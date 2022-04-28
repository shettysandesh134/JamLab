package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.user.Location
import com.sandeshshetty.jamlab.business.domain.model.user.ProfileImage
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.PatientNetworkEntity
import javax.inject.Inject


class PatientNetworkMapper
@Inject
constructor(
    private val locationNetworkMapper: LocationNetworkMapper
): EntityMapper<PatientNetworkEntity, User> {

    override fun mapFromEntity(entity: PatientNetworkEntity): User {
        return User(
            id = entity.id,
            lid = entity.lid,
            profileImage = entity.profile?.let { profile ->
                ProfileImage(
                    id = profile.id,
                    uid = profile.uid,
                    referenceId = profile.referenceId,
                    reference = profile.reference,
                    name = profile.name,
                    description = profile.description,
                    extention = profile.extention,
                    uri = profile.uri,
                    fileSize = profile.fileSize,
                    fileData = profile.fileData,
                    type = profile.type,
                    status = profile.status

                )
            },
            fname = entity.fname,
            lname = entity.lname,
            description = entity.description,
            gender = entity.gender,
            dob = entity.dob,
            bodytype = entity.bodytype,
            identification = entity.identification,
            occupation = entity.occupation,
            email = entity.email,
            mobile = entity.mobile,
            nationality = entity.nationality,
            medicareNo = entity.medicareNo,
            medicareRef = entity.medicareRef,
            memberNo = entity.memberNo,
            memberRef = entity.memberRef,
            emergencyRelation = entity.emergencyRelation,
            emergencyName = entity.emergencyName,
            emergencyMobile = entity.emergencyMobile,
            privateHealthName = entity.privateHealthName,
            privateHealthMobile = entity.privateHealthMobile,
            privateHealthRef = entity.privateHealthRef,
            otherHealthName = entity.otherHealthName,
            otherHealthMobile = entity.otherHealthMobile,
            otherHealthRef = entity.otherHealthRef,
            verified = entity.verified,
            channel = entity.channel,
            rating = entity.rating,
            type = entity.type,
            status = entity.status,
            location = entity.location?.let {
                locationNetworkMapper.mapFromEntity(it)
            }
        )

    }

    override fun mapToEntity(domainModel: User): PatientNetworkEntity {
        TODO("Not yet implemented")
    }
}