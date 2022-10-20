package com.example.lastfmapp.main.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lastfmapp.util.Constants

@Entity(tableName = Constants.TABLE_TAG)
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val tagId: String,
    val count: Int,
    val name: String,
    val url: String
)
