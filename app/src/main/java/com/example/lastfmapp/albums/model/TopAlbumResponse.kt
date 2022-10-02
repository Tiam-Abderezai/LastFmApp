package com.example.lastfmapp.albums.model

import com.google.gson.annotations.SerializedName

data class TopAlbumResponse(
    @SerializedName("topalbums")
    val topAlbums: Albums
)
