package com.example.lastfmapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.util.Converters

@Database(entities = [AlbumEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}
