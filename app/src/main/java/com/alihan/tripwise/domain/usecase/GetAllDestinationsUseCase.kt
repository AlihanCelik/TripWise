package com.alihan.tripwise.domain.usecase

import com.alihan.tripwise.di.model.Destination
import com.alihan.tripwise.domain.repository.DestinationRepository
import javax.inject.Inject

class GetAllDestinationsUseCase @Inject constructor(
    private val repository: DestinationRepository
) {
    suspend operator fun invoke(limit: Int = 10, offset: Int = 0): List<Destination> {
        return repository.getAllDestinations(limit, offset)
    }
}
