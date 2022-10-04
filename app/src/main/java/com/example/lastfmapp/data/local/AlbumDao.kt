package com.example.lastfmapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.lastfmapp.albums.model.AlbumEntity
import com.example.lastfmapp.util.Constants

@Dao
interface AlbumDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)

    @Query("SELECT * FROM ${Constants.TABLE_ALBUM}")
    fun observeAllAlbums(): LiveData<List<AlbumEntity>>
}
