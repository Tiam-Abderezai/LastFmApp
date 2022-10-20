package com.example.lastfmapp.util

import androidx.room.TypeConverter
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromArtist(artist: ArtistEntity) = Gson().toJson(artist)

    @TypeConverter
    fun toArtist(s: String) = Gson().fromJson(s, ArtistEntity::class.java)

    @TypeConverter
    fun fromImageList(images: List<ImageEntity>) = Gson().toJson(images)

    @TypeConverter
    fun toImageList(s: String) = Gson().fromJson(s, Array<ImageEntity>::class.java).toList()
}
