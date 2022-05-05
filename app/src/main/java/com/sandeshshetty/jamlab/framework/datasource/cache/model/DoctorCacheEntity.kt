package com.sandeshshetty.jamlab.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.ClinicNetworkEntity
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.QualificationNetworkEntity
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.SpecialityNetworkEntity
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.SymptomNetworkEntity
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.LocationNetworkEntity

@Entity(tableName = "doctors")
class DoctorCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "lid")
    val lid: Int,

//   val profile:
    @ColumnInfo(name = "fname")
    val fname: String? = null,

    @ColumnInfo(name = "lname")
    val lname: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "doctortype")
    val doctortype: String? = null,

    @ColumnInfo(name = "website")
    val website: String? = null,

    @ColumnInfo(name = "gender")
    val gender: String? = null,

    @ColumnInfo(name = "dob")
    val dob: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "phone")
    val phone: String? = null,

    @ColumnInfo(name = "practitioner_registration_number")
    val practionerRegistrationNumber: String? = null,

    @ColumnInfo(name = "experience")
    val experience: String? = null,

    @ColumnInfo(name = "language")
    val language: String? = null,

    @ColumnInfo(name = "timezone")
    val timezone: String? = null,

    @ColumnInfo(name = "digital_id_status")
    val digitalIdStatus: String? = null,

    @ColumnInfo(name = "police_check_status")
    val policeCheckStatus: String? = null,

    @ColumnInfo(name = "price")
    val price: String? = null,

    @ColumnInfo(name = "rating")
    val rating: String? = null,

    @ColumnInfo(name = "channel")
    val channel: String? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "relevance")
    val relevance: String? = null,

//    @ColumnInfo(name = "location")
//    @Embedded val location: LocationCacheEntity? = null,
    val locationId: Int?= null,

//    @ColumnInfo(name = "clinics")
//    val clinics: List<ClinicCacheEntity>? = null,
//
//    @ColumnInfo(name = "speciality")
//    val specialities: List<SpecialityCacheEntity>? = null,
//
//    @ColumnInfo(name = "symptoms")
//    val symptoms: List<SymptomCacheEntity>? = null,
//
//    @ColumnInfo(name = "qualifications")
//    val qualifications: List<QualificationCacheEntity>? = null
) {

}