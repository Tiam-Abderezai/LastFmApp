package com.example.lastfmapp.util

import androidx.room.TypeConverter
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.albums.model.Albums
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun fromAlbumList(albums: Albums): String = Gson().toJson(albums)

    @TypeConverter
    fun toAlbumList(s: String) = listOf(Gson().fromJson(s, Albums::class.java))

    @TypeConverter
    fun fromAlbum(album: AlbumEntity) = Gson().toJson(album)

    @TypeConverter
    fun toAlbum(s: String) = Gson().fromJson(s, AlbumEntity::class.java)

    @TypeConverter
    fun fromArtist(artist: ArtistEntity) = Gson().toJson(artist)

    @TypeConverter
    fun toArtist(s: String) = Gson().fromJson(s, ArtistEntity::class.java)

    @TypeConverter
    fun fromImageEntityList(image: ImageEntity) = Gson().toJson(image)

    @TypeConverter
    fun toImageEntityList(s: String) = Gson().fromJson(s, ImageEntity::class.java)

    @TypeConverter
    fun fromImageRequestList(images: List<ImageRequest>) = Gson().toJson(images)

    @TypeConverter
    fun toImageRequestList(s: String) = listOf(Gson().fromJson(s, ImageRequest::class.java))

    @TypeConverter
    fun fromTrack(track: TrackEntity): String = Gson().toJson(track)

    @TypeConverter
    fun toTrack(s: String) = Gson().fromJson(s, TrackEntity::class.java)

//    @TypeConverter
//    fun fromTrackEntityList(tracks: List<TrackEntity>?): String = Gson().toJson(tracks)
//
//    @TypeConverter
//    fun toTrackEntityList(s: String) = Gson().fromJson(s, TrackEntity::class.java))

    @TypeConverter
    fun fromTrackEntityList(data: String?): List<TrackEntity> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<TrackEntity>>() {
        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun toTrackEntityList(someObjects: List<TrackEntity>): String {
        return Gson().toJson(someObjects)
    }

//    @TypeConverter
//    fun fromTrackRequestList(tracks: List<TrackRequest>?): String = Gson().toJson(tracks)
//
//    @TypeConverter
//    fun toTrackRequestList(s: String) = listOf(Gson().fromJson(s, TrackRequest::class.java))
}
