package com.example.domain.repository

import com.example.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getRealTimeLocation(): Flow<Location>
}