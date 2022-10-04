package com.example.lastfmapp.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants

data class ImageRequest(
    val text: String?,
    val size: String
)

@Entity(tableName = Constants.TABLE_IMAGE)
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val text: String?,
    val size: String
)
