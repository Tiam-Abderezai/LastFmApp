package com.example.lastfmapp.main.albums.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.lastfmapp.databinding.FragmentAlbumDetailBinding
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {
    private lateinit var _bind: FragmentAlbumDetailBinding
    private val bind get() = _bind
    private val args by navArgs<AlbumDetailFragmentArgs>()
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        val albumName = args.albumEntity.name
        Log.d(Log.TAG, "albumName: $albumName")
        val albumArtist = args.albumEntity.artist.name
        val albumUrl = args.albumEntity.url
        bind.albumDescription.text = "$albumName is an album by $albumArtist."
    }
}
