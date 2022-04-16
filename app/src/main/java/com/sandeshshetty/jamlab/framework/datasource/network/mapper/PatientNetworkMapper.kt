package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.user.Location
import com.sandeshshetty.jamlab.business.domain.model.user.ProfileImage
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.PatientNetworkEntity


class PatientNetworkMapper: EntityMapper<PatientNetworkEntity, User> {

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
                Location(
                    id = entity.location.id,
                    address = entity.location.address,
                    country = entity.location.country,
                    state = entity.location.state,
                    city = entity.location.city,
                    street = entity.location.street,
                    postcode = entity.location.postcode,
                    lat = entity.location.lat,
                    long = entity.location.long,
                    type = entity.location.type,
                    status = entity.location.status
                )
            }
        )

    }

    override fun mapToEntity(domainModel: User): PatientNetworkEntity {
        TODO("Not yet implemented")
    }
}