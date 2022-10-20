package com.example.lastfmapp.main.albums.model

import com.google.gson.annotations.SerializedName

data class TopAlbumResponse(
    @SerializedName("topalbums")
    val topAlbums: Albums
)
