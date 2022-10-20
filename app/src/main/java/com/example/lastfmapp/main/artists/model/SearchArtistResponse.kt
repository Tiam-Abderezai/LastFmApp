package com.example.lastfmapp.main.artists.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistMatches(
    val id: String,
    @SerializedName("artistmatches")
    val artistsMatched: Artists
) : Parcelable

data class SearchArtistResponse(
    val results: ArtistMatches
)
