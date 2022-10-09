package com.example.lastfmapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {
//    private val _mainViewPager = MutableLiveData<MainPagerAdapter()>

    private val _albumsFragmentLiveData = MutableLiveData<String>()
    var albumsFragmentLiveData: LiveData<String> = _albumsFragmentLiveData

    private val _artistsFragmentLiveData = MutableLiveData<String>()
    var artistsFragmentLiveData: LiveData<String> = _artistsFragmentLiveData

    fun setAlbumsFragment(email: String) {
        _albumsFragmentLiveData.value = email
    }

    fun setSearchArtists(phone: String) {
        _artistsFragmentLiveData.value = phone
    }
}
