package com.sandeshshetty.jamlab.di

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.sandeshshetty.jamlab.BaseApplication
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.network.implementation.MedicalNetworkDataSourceImpl
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.implementation.DataStoreRepositoryImpl
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.cache.database.MedicalDao
import com.sandeshshetty.jamlab.framework.datasource.cache.database.MedicalDatabase
import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.datasource.network.implementation.MedicalNetworkServiceImpl
import com.sandeshshetty.jamlab.framework.datasource.network.mapper.*
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
//        return app as BaseApplication
//    }

    @Singleton
    @Provides
    fun provideRandomString(): String {
        return "Dagger Setup String"
    }

    @Singleton
    @Provides
    fun providePlacesClient(
        @ApplicationContext context: Context
    ): PlacesClient = Places.createClient(context)

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideLocationNetworkMapper(): LocationNetworkMapper {
        return LocationNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideSymptomNetworkMapper(): SymptomNetworkMapper {
        return SymptomNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideQualificationNetworkMapper(): QualificationNetworkMapper {
        return QualificationNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideClinicNetworkMapper(
        locationNetworkMapper: LocationNetworkMapper
    ): ClinicNetworkMapper {
        return ClinicNetworkMapper(locationNetworkMapper)
    }

    @Singleton
    @Provides
    fun providePatientNetworkMapper(
        locationNetworkMapper: LocationNetworkMapper
    ): PatientNetworkMapper {
        return PatientNetworkMapper(locationNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideSpecialityNetworkMapper(): SpecialityNetworkMapper {
        return SpecialityNetworkMapper()
    }

    @Singleton
    @Provides
    fun providesListNetworkMappers(
        doctorNetworkMapper: DoctorNetworkMapper,
        patientNetworkMapper: PatientNetworkMapper,
        specialityNetworkMapper: SpecialityNetworkMapper
    ): ListNetworkMappers {
        return ListNetworkMappers(
            doctorNetworkMapper,
            patientNetworkMapper,
            specialityNetworkMapper
        )
    }

    @Singleton
    @Provides
    fun provideMedicalNetworkService(
        medicalRepository: MedicalRepository,
        listNetworkMappers: ListNetworkMappers
    ): MedicalNetworkService {
        return MedicalNetworkServiceImpl(medicalRepository, listNetworkMappers)
    }

    @Singleton
    @Provides
    fun provideMedicalNetworkDataSource(
        medicalNetworkService: MedicalNetworkServiceImpl,
    ): MedicalNetworkDataSource {
        return MedicalNetworkDataSourceImpl(medicalNetworkService)
    }

    @Singleton
    @Provides
    fun provideMedicalDao(medicalDatabase: MedicalDatabase): MedicalDao {
        return medicalDatabase.medicalDao()
    }

}