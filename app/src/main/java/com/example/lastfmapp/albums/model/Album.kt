package com.example.lastfmapp.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.artists.model.ArtistEntity
import com.example.lastfmapp.artists.model.ArtistRequest
import com.example.lastfmapp.util.Constants.Companion.TABLE_ALBUM
import com.google.gson.annotations.SerializedName

data class AlbumRequest(
    val artist: ArtistRequest,
    @SerializedName("image")
    val images: List<ImageRequest>,
    @SerializedName("mbid")
    val mBid: String,
    val name: String,
    val url: String
)

@Entity(tableName = TABLE_ALBUM)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val artist: ArtistEntity,
    val image: List<ImageEntity>,
    val mBid: String,
    val name: String,
    val url: String
)
