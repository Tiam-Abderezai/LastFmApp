package com.example.lastfmapp.artists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    @SerialName("name") val name: String = "",
    @SerialName("playcount") val playcount: Int = 0,
    @SerialName("url") val url: String = ""
//    @SerialName("artist") val artist: Artist = Artist(),
//    @SerialName("image") val image: List<Image> = listOf()
)
