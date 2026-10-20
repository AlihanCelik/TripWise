package com.alihan.tripwise.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OpenTripMapDetailDto(
    @SerializedName("xid")
    val xid: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("preview")
    val preview: PreviewDto?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("address")
    val address: AddressDto?,
    @SerializedName("wikipedia_extracts")
    val wikipediaExtracts: WikipediaExtractsDto?
)

data class PreviewDto(
    @SerializedName("source")
    val source: String?
)

data class AddressDto(
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?
)

data class WikipediaExtractsDto(
    @SerializedName("text")
    val text: String?
)
