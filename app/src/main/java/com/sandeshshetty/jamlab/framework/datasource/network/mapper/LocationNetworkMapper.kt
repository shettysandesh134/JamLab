package com.sandeshshetty.jamlab.framework.datasource.network.mapper

import com.sandeshshetty.jamlab.business.domain.model.user.Location
import com.sandeshshetty.jamlab.business.domain.util.EntityMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.LocationNetworkEntity

class LocationNetworkMapper: EntityMapper<LocationNetworkEntity, Location> {
    
    override fun mapFromEntity(entity: LocationNetworkEntity): Location {
        return Location(
            id = entity.id,
        address = entity.address,
        country = entity.country,
        state = entity.state,
        city = entity.city,
        street = entity.street,
        postcode = entity.postcode,
        lat = entity.lat,
        long = entity.long,
        type = entity.type,
        status = entity.status
        )    
    }

    override fun mapToEntity(domainModel: Location): LocationNetworkEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(locations: List<LocationNetworkEntity>?): List<Location>? {
        return locations?.map {
            mapFromEntity(it)
        }
        return null
    }

}