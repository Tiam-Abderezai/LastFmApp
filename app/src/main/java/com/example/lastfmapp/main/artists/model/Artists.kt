package com.example.lastfmapp.main.artists.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    @SerializedName("artist")
    val artists: List<ArtistRequest>
) : Parcelable
