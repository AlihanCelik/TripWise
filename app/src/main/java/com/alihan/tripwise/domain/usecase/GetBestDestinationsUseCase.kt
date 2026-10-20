package com.alihan.tripwise.domain.usecase

import com.alihan.tripwise.di.model.Destination
import com.alihan.tripwise.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBestDestinationsUseCase @Inject constructor(
    private val repository: DestinationRepository
) {
    operator fun invoke(): Flow<List<Destination>> {
        return repository.getBestDestinations()
    }
}
