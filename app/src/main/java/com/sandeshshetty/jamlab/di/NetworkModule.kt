package com.sandeshshetty.jamlab.di

import com.google.gson.GsonBuilder
import com.sandeshshetty.jamlab.MedicalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMedicalServices(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://medical.pdbsoftware.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Singleton
    @Provides
    fun provideMedicalService(retrofit: Retrofit): MedicalService {
        return retrofit.create(MedicalService::class.java)
    }

}