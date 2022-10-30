package com.example.lastfmapp.data.local

import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    // Album Entity
    fun queryAlbumEntities(): Flow<List<AlbumEntity>>
    fun doesAlbumExist(mBid: String?): Boolean
    fun queryAlbumEntity(mBid: String?): AlbumEntity
    suspend fun insertAlbum(album: AlbumEntity)
    suspend fun deleteAlbum(album: AlbumEntity)

    // Track Entity List
    fun queryTrackEntities(trackId: String): Flow<List<TrackEntity>>
    suspend fun insertTracksEntity(tracks: List<TrackEntity>?)
    suspend fun deleteTracksEntity(tracks: List<TrackEntity>?)

    // Artist Entity
    suspend fun saveArtist(artist: ArtistEntity)
}
