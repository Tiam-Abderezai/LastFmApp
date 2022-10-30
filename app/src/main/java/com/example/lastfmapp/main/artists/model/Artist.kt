package com.example.lastfmapp.main.artists.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants.Companion.TABLE_ARTIST
import com.example.lastfmapp.util.ImageRequest
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistRequest(
    @SerializedName("mbid")
    val mBid: String,
    val name: String,
    val listeners: Int,
    val url: String,
    @SerializedName("image")
    val images: List<ImageRequest>?
) : Parcelable {
    override fun toString() = name
}

@Entity(tableName = TABLE_ARTIST)
@Parcelize
data class ArtistEntity(
    @PrimaryKey
    val name: String,
    val mBid: String?,
    val url: String?
) : Parcelable
