package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.lastfmapp.albums.model.Album
import com.example.lastfmapp.util.Resource
import com.example.lastfmapp.retrofit.RetrofitRepository
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    val app: Application,
    private val retrofitRepository: RetrofitRepository
) : ViewModel() {

    private val _albumListLiveData = MutableLiveData<List<Album>>()
    val albumListLiveData: LiveData<List<Album>> = _albumListLiveData

    init {
        Log.d(TAG, "")
    }

    fun getTopAlbums() = liveData(Dispatchers.IO) {
        val topAlbums = retrofitRepository.getTopAlbums().body()?.topAlbums
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = topAlbums))
            val album1 = topAlbums?.albums?.get(0)
            val album2 = topAlbums?.albums?.get(1)
            val album3 = topAlbums?.albums?.get(2)
            _albumListLiveData.postValue(listOf(album1, album2, album3) as List<Album>?)
        } catch (exception: Exception) {
            exception.message?.let { Log.d(TAG, it) }
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
//        }
    }
}
