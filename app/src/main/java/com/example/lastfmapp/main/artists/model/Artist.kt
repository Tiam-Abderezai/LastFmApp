package com.example.lastfmapp.main.artists.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants.Companion.TABLE_ARTIST
import com.example.lastfmapp.util.ImageRequest
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistRequest(
    @SerializedName("mbid")
    val mBid: String,
    val name: String,
    val listeners: Int,
    val url: String,
    @SerializedName("image")
    val images: List<ImageRequest>?
) : Parcelable

@Entity(tableName = TABLE_ARTIST)
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val mBid: String,
    val name: String,
    val url: String
)
