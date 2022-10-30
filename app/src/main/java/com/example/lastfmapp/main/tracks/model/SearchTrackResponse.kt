package com.example.lastfmapp.main.tracks.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackMatches(
    val id: String,
    @SerializedName("trackmatches")
    val tracksMatched: Tracks
) : Parcelable

data class SearchTrackResponse(
    val results: TrackMatches
)
