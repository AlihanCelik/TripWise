package com.alihan.tripwise.data.repository

import com.alihan.tripwise.data.datasource.RemoteDestinationDataSource
import com.alihan.tripwise.di.model.Destination
import com.alihan.tripwise.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDestinationDataSource
) : DestinationRepository {

    override fun getBestDestinations(): Flow<List<Destination>> = flow {
        try {
            val remotePlaces = remoteDataSource.getBestDestinationsInTurkey(limit = 10)
            emit(remotePlaces)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun getAllDestinations(limit: Int, offset: Int): List<Destination> {
        return try {
            remoteDataSource.getPaginatedDestinationsInTurkey(limit = limit, offset = offset)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
