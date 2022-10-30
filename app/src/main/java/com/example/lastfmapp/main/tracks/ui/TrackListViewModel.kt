package com.example.lastfmapp.main.tracks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.data.local.RoomRepositoryImpl
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.main.tracks.model.TrackEntity
import com.example.lastfmapp.main.tracks.model.TrackRequest
import com.example.lastfmapp.util.ImageEntity
import com.example.lastfmapp.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackListViewModel @Inject constructor(
    private val retrofitRepoImpl: RetrofitRepositoryImpl,
    private val roomRepoImpl: RoomRepositoryImpl
) : ViewModel() {

    private val _trackListLiveData = MutableLiveData<List<TrackRequest>?>()
    val trackListLiveData: LiveData<List<TrackRequest>?> = _trackListLiveData
    private val _trackQueryLiveData = MutableLiveData<List<TrackRequest>?>()
    val trackQueryLiveData: LiveData<List<TrackRequest>?> = _trackQueryLiveData

    private val _isEntitySaved = MutableLiveData<Boolean>()
    val isEntitySaved: LiveData<Boolean> = _isEntitySaved
    var isSaved: Boolean = false

    private val _albumEntitiesLiveData: LiveData<List<AlbumEntity>> =
        roomRepoImpl.queryAlbumEntities().asLiveData()
    val albumEntitiesLiveData: LiveData<List<AlbumEntity>> = _albumEntitiesLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tracks = trackQueryLiveData.value
            if (trackQueryLiveData.value != null) {
                _trackListLiveData.postValue(tracks!!)
            }
        }
    }

    fun queryTrack(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = retrofitRepoImpl.getTracks(query).results.tracksMatched.tracks
            Log.d(Log.TAG, "Album Query: $query")
            Log.d(Log.TAG, "Tracks Response: $response")
            _trackQueryLiveData.postValue(response)
        }
    }

    private fun saveTopAlbum(album: AlbumEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepoImpl.insertAlbum(album)
            Log.d(Log.TAG, "Album Entity: $album")
        }
    }

    private fun deleteTopAlbum(album: AlbumEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepoImpl.deleteAlbum(album)
            Log.d(Log.TAG, "Album Entity: $album")
        }
    }

    fun handleLikedAlbum(albumRequest: AlbumRequest, tracks: List<TrackRequest>?) {
        viewModelScope.launch(Dispatchers.IO) {
            val entityTracks = getTrackEntities(tracks)
            val entityAlbum = getAlbumEntity(albumRequest, entityTracks)
            Log.d(Log.TAG, "tracks: $tracks")
            Log.d(Log.TAG, "entityTracks: $entityTracks")
            Log.d(Log.TAG, "albumEntity.mBid: ${albumRequest.mBid}")
            isSaved = roomRepoImpl.doesAlbumExist(albumRequest.mBid)
            if (isSaved) {
                deleteTopAlbum(entityAlbum)
                _isEntitySaved.postValue(isSaved)
                Log.d(Log.TAG, "isSaved True: $isSaved")
            } else {
                saveTopAlbum(entityAlbum)
                _isEntitySaved.postValue(isSaved)
                Log.d(Log.TAG, "isSaved False: $isSaved")
            }
        }
    }

    fun isAlbumLiked(albumRequest: AlbumRequest, tracks: List<TrackRequest>?) {
        viewModelScope.launch(Dispatchers.IO) {
            val entityTracks = getTrackEntities(tracks)
            Log.d(Log.TAG, "tracks: $tracks")
            Log.d(Log.TAG, "entityTracks: $entityTracks")
            Log.d(Log.TAG, "albumEntity.mBid: ${albumRequest.mBid}")
            isSaved = roomRepoImpl.doesAlbumExist(albumRequest.mBid)
            Log.d(Log.TAG, "isSaved: $isSaved")
            _isEntitySaved.postValue(isSaved)
        }
    }

    private fun getAlbumEntity(
        albumRequest: AlbumRequest,
        trackEntities: List<TrackEntity>
    ): AlbumEntity {
        val albumName = albumRequest.name
        val albummBid = albumRequest.mBid
        val albumUrl = albumRequest.url
        val artistName = albumRequest.artist.name
        val artistmBid = albumRequest.artist.mBid
        val artistUrl = albumRequest.artist.url
        val albumImageSize = albumRequest.images[0].size
        val albumImageUrl = albumRequest.images[0].text
        val artistEntity =
            ArtistEntity(mBid = artistmBid, name = artistName, url = artistUrl)
        val imageEntity =
            ImageEntity(
                text = albumImageUrl,
                size = albumImageSize.get(0).toString()
            )
        return AlbumEntity(
            mBid = albummBid ?: "",
            artist = artistEntity,
            image = imageEntity,
            name = albumName,
            url = albumUrl,
            tracks = trackEntities
        )
    }

    private fun getTrackEntities(trackRequests: List<TrackRequest>?): List<TrackEntity> {
        val trackEntities: MutableList<TrackEntity> = ArrayList()
        trackRequests?.forEach { request ->
            val trackName = request.name
            val trackDuration = request.duration
            val trackmBid = request.mBid
            val trackArtist = request.artist
            val trackUrl = request.url

            trackEntities.add(
                TrackEntity(
                    name = trackName,
                    duration = trackDuration,
                    mBid = trackmBid ?: "",
                    artist = trackArtist,
                    url = trackUrl
                )
            )
        }
        return trackEntities.toList()
    }
}
