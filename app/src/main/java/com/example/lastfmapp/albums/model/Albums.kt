package com.example.lastfmapp.albums.model

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("album") val albums: List<Album>,
    @SerializedName("@attr") val attr: Attr
)
