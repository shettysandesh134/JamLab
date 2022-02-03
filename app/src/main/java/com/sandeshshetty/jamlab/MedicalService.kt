package com.sandeshshetty.jamlab

import com.sandeshshetty.jamlab.business.model.User
import retrofit2.http.Headers
import retrofit2.http.POST

interface MedicalService {

    @Headers("content-type: application/json")
    @POST
    suspend fun login(

    ): User?

}