package com.example.lastfmapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {
//    @Insert(onConflict = REPLACE)
//    suspend fun insertTracksRequest(tracks: List<TrackRequest>?)

    @Insert(onConflict = REPLACE)
    suspend fun insertTracksEntity(tracks: List<TrackEntity>?)

//    @Delete
//    suspend fun deleteTracksRequest(tracks: List<TrackRequest>?)

    @Delete
    suspend fun deleteTracksEntity(tracks: List<TrackEntity>?)

    @Query("SELECT * FROM ${Constants.TABLE_TRACKS} ORDER BY name DESC")
    fun observeAllTracks(): Flow<List<TrackEntity>>
}
