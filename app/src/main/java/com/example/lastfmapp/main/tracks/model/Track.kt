package com.example.lastfmapp.main.tracks.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants
import com.example.lastfmapp.util.ImageRequest
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TrackRequest(
    @PrimaryKey
    val name: String,
    val artist: String,
    val listeners: Int,
    val duration: Int,
    val mBid: String?,
    val url: String,
    @SerializedName("image")
    val images: List<ImageRequest>?
) : Parcelable

@Entity(tableName = Constants.TABLE_TRACKS)
@Parcelize
data class TrackEntity(
    @PrimaryKey
    val name: String,
    val duration: Int = 0,
    val mBid: String?,
    val url: String?,
    val artist: String?
) : Parcelable