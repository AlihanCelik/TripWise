package com.alihan.tripwise.data.datasource

import com.alihan.tripwise.R
import com.alihan.tripwise.di.model.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeDestinationDataSource @Inject constructor() {
    fun getBestDestinations(): Flow<List<Destination>> {
        return flowOf(
            listOf(
                Destination("1", "Paris", 4.8, "France", "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg", R.drawable.img_1),
                Destination("2", "Tokyo", 4.7, "Japan", "", R.drawable.img_1),
                Destination("3", "Istanbul", 4.5, "Turkey", "", R.drawable.img_1),
                Destination("4", "New York", 4.6, "USA", "", R.drawable.img_1),
                Destination("5", "Rome", 4.4, "Italy", "", R.drawable.img_1)
            )
        )
    }
}
