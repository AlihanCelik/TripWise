package com.alihan.tripwise.data.remote.api

import com.alihan.tripwise.data.remote.dto.OpenTripMapDetailDto
import com.alihan.tripwise.data.remote.dto.OpenTripMapPlaceDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenTripMapApiService {

    @GET("places/bbox")
    suspend fun getPlacesInBbox(
        @Query("lon_min") lonMin: Double = 26.0,
        @Query("lon_max") lonMax: Double = 45.0,
        @Query("lat_min") latMin: Double = 36.0,
        @Query("lat_max") latMax: Double = 42.0,
        @Query("rate") rate: Int = 3,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10,
        @Query("apikey") apiKey: String
    ): List<OpenTripMapPlaceDto>

    @GET("places/xid/{xid}")
    suspend fun getPlaceDetails(
        @Path("xid") xid: String,
        @Query("apikey") apiKey: String
    ): OpenTripMapDetailDto
}
