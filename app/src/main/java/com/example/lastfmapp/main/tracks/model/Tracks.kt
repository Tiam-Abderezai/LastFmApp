package com.example.lastfmapp.main.tracks.model
import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tracks(
    @PrimaryKey(autoGenerate = true)
    val tracksId: String,
    @SerializedName("track")
    val tracks: List<TrackRequest>?
) : Parcelable
