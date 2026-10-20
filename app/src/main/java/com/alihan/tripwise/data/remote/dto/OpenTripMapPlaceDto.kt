package com.alihan.tripwise.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OpenTripMapPlaceDto(
    @SerializedName("xid")
    val xid: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("kinds")
    val kinds: String?,
    @SerializedName("point")
    val point: PointDto?
)

data class PointDto(
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("lat")
    val lat: Double?
)
