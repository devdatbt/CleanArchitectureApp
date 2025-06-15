package com.example.domain.usecase

import com.example.domain.model.Location
import com.example.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetLastKnownLocationUseCase(private val repository: LocationRepository){
    suspend fun invoke() : Flow<Location> {
        return repository.getRealTimeLocation()
    }
}