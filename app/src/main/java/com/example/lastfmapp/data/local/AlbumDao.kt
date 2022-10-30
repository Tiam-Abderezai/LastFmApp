package com.example.lastfmapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)

    @Query("SELECT * FROM ${Constants.TABLE_ALBUM} ORDER BY mBid DESC")
    fun observeAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM ${Constants.TABLE_ALBUM} WHERE mBid = :mBid)")
    fun doesAlbumExist(mBid: String): Boolean

    @Query("SELECT * FROM ${Constants.TABLE_ALBUM} WHERE mBid = :mBid")
    fun queryAlbum(mBid: String): AlbumEntity
}
