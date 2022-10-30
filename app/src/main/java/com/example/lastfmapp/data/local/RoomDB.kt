package com.example.lastfmapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.Converters

@Database(entities = [AlbumEntity::class, ArtistEntity::class, TrackEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun tracksDao(): TracksDao
    abstract fun artistDao(): ArtistDao
}
