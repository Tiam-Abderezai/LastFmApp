package com.example.lastfmapp.albums

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.lastfmapp.api.ApiRepository
import com.example.lastfmapp.api.ApiResource
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(val app: Application, private val apiRepository: ApiRepository) : ViewModel() {

    private val _albumListLiveData = MutableLiveData<List<Album>>()
    val albumListLiveData: LiveData<List<Album>> = _albumListLiveData

    init {
        Log.d(TAG, "")
    }

    fun getAlbumInfo() = liveData(Dispatchers.IO) {
        viewModelScope.launch(Dispatchers.IO) {
            emit(ApiResource.loading(data = null))
            try {
                emit(ApiResource.success(data = apiRepository.getAlbumInfo()))
                _albumListLiveData.postValue(listOf(Album("Album 1"), Album("Album2"), Album("Album3")))
            } catch (exception: Exception) {
                exception.message?.let { Log.d(Log.TAG, it) }
                emit(
                    ApiResource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}
