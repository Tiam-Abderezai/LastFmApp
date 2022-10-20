package com.example.lastfmapp.main.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.TABLE_ALBUMS)
data class Albums(
    @PrimaryKey(autoGenerate = true)
    val albumsId: String,
    @SerializedName("album")
    val albums: List<AlbumRequest>,
    @SerializedName("@attr")
    val attr: Attr
)
