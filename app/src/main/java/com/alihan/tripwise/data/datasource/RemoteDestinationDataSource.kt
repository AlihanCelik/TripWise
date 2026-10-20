package com.alihan.tripwise.data.datasource

import com.alihan.tripwise.R
import com.alihan.tripwise.data.remote.api.OpenTripMapApiService
import com.alihan.tripwise.di.model.Destination
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDestinationDataSource @Inject constructor(
    private val apiService: OpenTripMapApiService
) {
    private val apiKey = "5ae2e3f221c38a28845f05b68b6dc789163dd0c0c2ab9241808a39dd"

    suspend fun getBestDestinationsInTurkey(limit: Int = 5): List<Destination> = coroutineScope {
        try {
            val placesDto = apiService.getPlacesInBbox(
                rate = 3,
                limit = 15,
                apiKey = apiKey
            )

            val validPlaces = placesDto.filter { !it.name.isNull_or_empty() }.take(limit)

            val deferredDetails = validPlaces.map { place ->
                async {
                    try {
                        val detail = apiService.getPlaceDetails(place.xid, apiKey)
                        val addressParts = listOfNotNull(
                            detail.address?.city ?: detail.address?.state,
                            detail.address?.country ?: "Turkey"
                        ).filter { it.isNotBlank() }

                        val addressText = if (addressParts.isNotEmpty()) {
                            addressParts.joinToString(", ")
                        } else {
                            "Turkey"
                        }

                        val imageUrl = detail.preview?.source
                            ?: detail.image
                            ?: "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg"

                        Destination(
                            id = detail.xid,
                            name = detail.name ?: place.name ?: "Turkey Destination",
                            rating = if ((detail.rate ?: 0.0) > 0) (detail.rate ?: 4.5) else 4.5,
                            address = addressText,
                            imageUrl = imageUrl,
                            imageRes = R.drawable.img_1
                        )
                    } catch (e: Exception) {
                        Destination(
                            id = place.xid,
                            name = place.name ?: "Turkey Destination",
                            rating = 4.5,
                            address = "Turkey",
                            imageUrl = "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg",
                            imageRes = R.drawable.img_1
                        )
                    }
                }
            }

            deferredDetails.awaitAll()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPaginatedDestinationsInTurkey(limit: Int = 10, offset: Int = 0): List<Destination> = coroutineScope {
        try {
            val totalToFetch = limit + offset
            val placesDto = apiService.getPlacesInBbox(
                rate = 2, // Rate 2 or higher for more places on pagination
                limit = totalToFetch + 10,
                apiKey = apiKey
            )

            val validPlaces = placesDto.filter { !it.name.isNull_or_empty() }
                .drop(offset)
                .take(limit)

            val deferredDetails = validPlaces.map { place ->
                async {
                    try {
                        val detail = apiService.getPlaceDetails(place.xid, apiKey)
                        val addressParts = listOfNotNull(
                            detail.address?.city ?: detail.address?.state,
                            detail.address?.country ?: "Turkey"
                        ).filter { it.isNotBlank() }

                        val addressText = if (addressParts.isNotEmpty()) {
                            addressParts.joinToString(", ")
                        } else {
                            "Turkey"
                        }

                        val imageUrl = detail.preview?.source
                            ?: detail.image
                            ?: "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg"

                        Destination(
                            id = detail.xid,
                            name = detail.name ?: place.name ?: "Turkey Destination",
                            rating = if ((detail.rate ?: 0.0) > 0) (detail.rate ?: 4.5) else 4.5,
                            address = addressText,
                            imageUrl = imageUrl,
                            imageRes = R.drawable.img_1
                        )
                    } catch (e: Exception) {
                        Destination(
                            id = place.xid,
                            name = place.name ?: "Turkey Destination",
                            rating = 4.5,
                            address = "Turkey",
                            imageUrl = "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg",
                            imageRes = R.drawable.img_1
                        )
                    }
                }
            }

            deferredDetails.awaitAll()
        } catch (e: Exception) {
            emptyList()
        }
    }
}

private fun String?.isNull_or_empty(): Boolean = this == null || this.trim().isEmpty()
