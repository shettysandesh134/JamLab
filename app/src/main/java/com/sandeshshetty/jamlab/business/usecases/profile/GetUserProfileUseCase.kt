package com.sandeshshetty.jamlab.business.usecases.profile

import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import javax.inject.Inject

class GetUserProfileUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource,
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke() {

//        val accessToken = dataStoreRepository.getString(ACCESS_TOKEN)

    }

}