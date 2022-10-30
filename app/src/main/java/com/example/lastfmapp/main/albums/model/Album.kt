package com.example.lastfmapp.main.albums.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.Constants.Companion.TABLE_ALBUM
import com.example.lastfmapp.util.ImageEntity
import com.example.lastfmapp.util.ImageRequest
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumRequest(
    val name: String,
    val artist: ArtistRequest,
    @SerializedName("image") val images: List<ImageRequest>,
    @SerializedName("mbid") val mBid: String?,
    val url: String
) : Parcelable

@Entity(tableName = TABLE_ALBUM)
@Parcelize
data class AlbumEntity(
    @PrimaryKey
    val mBid: String,
    val artist: ArtistEntity?,
//    @SerializedName("track")
    val tracks: List<TrackEntity>?,
    val image: ImageEntity,
    val name: String,
    val url: String
) : Parcelable
