package com.alihan.tripwise.data.repository

import com.alihan.tripwise.data.datasource.FakeDestinationDataSource
import com.alihan.tripwise.domain.model.Destination
import com.alihan.tripwise.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val dataSource: FakeDestinationDataSource
) : DestinationRepository {
    override fun getBestDestinations(): Flow<List<Destination>> {
        return dataSource.getBestDestinations()
    }
}
