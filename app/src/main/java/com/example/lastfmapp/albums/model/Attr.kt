package com.example.lastfmapp.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants

@Entity(tableName = Constants.TABLE_ATTR)
data class Attr(
    @PrimaryKey(autoGenerate = true)
    val attrId: String,
    val artist: String,
    val page: String,
    val perPage: String,
    val total: String,
    val totalPages: String
)
