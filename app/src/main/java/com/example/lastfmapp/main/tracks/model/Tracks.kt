package com.example.lastfmapp.main.tracks.model

import android.os.Parcelable
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tracks(
    @SerializedName("track")
    val tracks: List<TrackRequest>
) : Parcelable