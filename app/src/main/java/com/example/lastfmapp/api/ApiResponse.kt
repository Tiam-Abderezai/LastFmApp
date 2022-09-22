package com.example.lastfmapp.api
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("artist") val artist: String,
    @SerializedName("name") val name: String
//    @SerializedName("image") val images: List<Image>,
//    @SerializedName("tracks") val tracks: Tracks,
//    @SerializedName("tags") val tags: TopTags,
//    @SerializedName("listeners") val listeners: Long,
//    @SerializedName("playcount") val playCount: Long
)
