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

    private val turkeyUnsplashImages = listOf(
        "https://images.unsplash.com/photo-1527838832700-5059252407fa?q=80&w=1000",
        "https://images.unsplash.com/photo-1641128324972-af3212f0f6bd?q=80&w=1000",
        "https://images.unsplash.com/photo-1584132967334-10e028bd69f7?q=80&w=1000",
        "https://images.unsplash.com/photo-1542051841857-5f90071e7989?q=80&w=1000",
        "https://images.unsplash.com/photo-1565008447742-97f6f38c985c?q=80&w=1000",
        "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?q=80&w=1000",
        "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=80&w=1000",
        "https://images.unsplash.com/photo-1469854523086-cc02fe5d8800?q=80&w=1000",
        "https://images.unsplash.com/photo-1510414842594-a61c69b5ae57?q=80&w=1000",
        "https://images.unsplash.com/photo-1501785888041-af3ef285b470?q=80&w=1000"
    )

    private fun resolveCoverImageUrl(rawUrl: String?, index: Int): String {
        if (!rawUrl.isNullOrBlank() && (rawUrl.startsWith("http://") || rawUrl.startsWith("https://"))) {
            return rawUrl
        }
        return turkeyUnsplashImages[index % turkeyUnsplashImages.size]
    }

    private fun resolveCityOrAddress(detailCity: String?, detailState: String?, detailCountry: String?, placeName: String?): String {
        val name = placeName ?: ""
        return when {
            !detailCity.isNullOrBlank() -> "$detailCity, Turkey"
            !detailState.isNullOrBlank() -> "$detailState, Turkey"
            name.contains("Istanbul", ignoreCase = true) || name.contains("İstanbul", ignoreCase = true) -> "Istanbul, Turkey"
            name.contains("Antalya", ignoreCase = true) -> "Antalya, Turkey"
            name.contains("Izmir", ignoreCase = true) || name.contains("İzmir", ignoreCase = true) -> "Izmir, Turkey"
            name.contains("Ankara", ignoreCase = true) -> "Ankara, Turkey"
            name.contains("Bursa", ignoreCase = true) -> "Bursa, Turkey"
            name.contains("Muğla", ignoreCase = true) || name.contains("Bodrum", ignoreCase = true) -> "Muğla, Turkey"
            else -> if (!detailCountry.isNullOrBlank()) detailCountry else "Turkey"
        }
    }

    suspend fun getBestDestinationsInTurkey(limit: Int = 10): List<Destination> = coroutineScope {
        try {
            val placesDto = apiService.getPlacesInBbox(
                rate = 1,
                limit = 50,
                apiKey = apiKey
            )

            val validPlaces = placesDto
                .filter { !it.name.isNull_or_empty() }
                .distinctBy { it.name?.trim()?.lowercase() }
                .distinctBy { it.xid }

            val deferredDetails = validPlaces.take(limit * 2).mapIndexed { index, place ->
                async {
                    try {
                        val detail = apiService.getPlaceDetails(place.xid, apiKey)
                        val addressText = resolveCityOrAddress(
                            detail.address?.city,
                            detail.address?.state,
                            detail.address?.country,
                            detail.name ?: place.name
                        )

                        val rawUrl = detail.preview?.source ?: detail.image
                        val imageUrl = resolveCoverImageUrl(rawUrl, index)

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
                            imageUrl = resolveCoverImageUrl(null, index),
                            imageRes = R.drawable.img_1
                        )
                    }
                }
            }

            deferredDetails.awaitAll()
                .distinctBy { it.name.trim().lowercase() }
                .distinctBy { it.id }
                .take(limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPaginatedDestinationsInTurkey(limit: Int = 10, offset: Int = 0): List<Destination> = coroutineScope {
        try {
            val placesDto = apiService.getPlacesInBbox(
                rate = 1,
                limit = 100,
                apiKey = apiKey
            )

            val validPlaces = placesDto
                .filter { !it.name.isNull_or_empty() }
                .distinctBy { it.name?.trim()?.lowercase() }
                .distinctBy { it.xid }

            val pagedPlaces = validPlaces.drop(offset).take(limit * 2)

            val deferredDetails = pagedPlaces.mapIndexed { index, place ->
                async {
                    try {
                        val detail = apiService.getPlaceDetails(place.xid, apiKey)
                        val addressText = resolveCityOrAddress(
                            detail.address?.city,
                            detail.address?.state,
                            detail.address?.country,
                            detail.name ?: place.name
                        )

                        val rawUrl = detail.preview?.source ?: detail.image
                        val imageUrl = resolveCoverImageUrl(rawUrl, offset + index)

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
                            imageUrl = resolveCoverImageUrl(null, offset + index),
                            imageRes = R.drawable.img_1
                        )
                    }
                }
            }

            deferredDetails.awaitAll()
                .distinctBy { it.name.trim().lowercase() }
                .distinctBy { it.id }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

private fun String?.isNull_or_empty(): Boolean = this == null || this.trim().isEmpty()
