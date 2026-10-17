package com.alihan.tripwise.domain.repository

import com.alihan.tripwise.domain.model.Destination
import kotlinx.coroutines.flow.Flow

interface DestinationRepository {
    fun getBestDestinations(): Flow<List<Destination>>
}
