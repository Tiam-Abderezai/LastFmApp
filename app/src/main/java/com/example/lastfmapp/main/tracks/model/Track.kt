package com.example.lastfmapp.main.tracks.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants
import com.example.lastfmapp.util.ImageRequest
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrackRequest(
    val name: String,
    val artist: String,
    val listeners: Int,
    val url: String,
    @SerializedName("image")
    val images: List<ImageRequest>?
) : Parcelable

