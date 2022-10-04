package com.example.lastfmapp.artists.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants.Companion.TABLE_ARTIST
import com.google.gson.annotations.SerializedName

data class ArtistRequest(
    @SerializedName("mbid")
    val mBid: String,
    val name: String,
    val url: String
)

@Entity(tableName = TABLE_ARTIST)
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val mBid: String,
    val name: String,
    val url: String
)

// data class SearchArtist(
//    val name: String,
//    val listeners: Int,
//    val mBid: String,
//    val url: String,
//    val streamable: Boolean,
//    val image: Image
// )
