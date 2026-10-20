package com.alihan.tripwise.domain.repository

import com.alihan.tripwise.di.model.Destination
import kotlinx.coroutines.flow.Flow

interface DestinationRepository {
    fun getBestDestinations(): Flow<List<Destination>>
    suspend fun getAllDestinations(limit: Int, offset: Int): List<Destination>
}
