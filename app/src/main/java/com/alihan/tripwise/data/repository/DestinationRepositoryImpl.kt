package com.alihan.tripwise.data.repository

import com.alihan.tripwise.data.datasource.FakeDestinationDataSource
import com.alihan.tripwise.data.datasource.RemoteDestinationDataSource
import com.alihan.tripwise.di.model.Destination
import com.alihan.tripwise.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDestinationDataSource,
    private val fakeDataSource: FakeDestinationDataSource
) : DestinationRepository {

    override fun getBestDestinations(): Flow<List<Destination>> = flow {
        try {
            val remotePlaces = remoteDataSource.getBestDestinationsInTurkey(limit = 5)
            if (remotePlaces.isNotEmpty()) {
                emit(remotePlaces)
            } else {
                emit(fakeDataSource.getBestDestinations().first())
            }
        } catch (e: Exception) {
            emit(fakeDataSource.getBestDestinations().first())
        }
    }

    override suspend fun getAllDestinations(limit: Int, offset: Int): List<Destination> {
        return try {
            val remotePlaces = remoteDataSource.getPaginatedDestinationsInTurkey(limit = limit, offset = offset)
            if (remotePlaces.isNotEmpty()) {
                remotePlaces
            } else {
                fakeDataSource.getBestDestinations().first()
            }
        } catch (e: Exception) {
            fakeDataSource.getBestDestinations().first()
        }
    }
}
