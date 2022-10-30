package com.example.lastfmapp.main.top_albums.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.FragmentTopAlbumListBinding
import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.main.MainFragmentDirections
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumListFragment : Fragment(), TopAlbumListAdapter.OnItemClickListener {

    private lateinit var _bind: FragmentTopAlbumListBinding
    private val bind get() = _bind
    private val topAlbumsViewModel by lazy { ViewModelProvider(this)[TopAlbumListViewModel::class.java] }
    private val args by navArgs<TopAlbumListFragmentArgs>()
    private lateinit var artistName: String
    private lateinit var artistImage: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentTopAlbumListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _bind = FragmentTopAlbumListBinding.bind(view)
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
        Log.d(Log.TAG, "")
        with(topAlbumsViewModel) {
            artistName = args.artistRequest.name
            queryTopAlbums(artistName)
            topAlbumQueryLiveData.observe(viewLifecycleOwner) { albumRequests ->
                Log.d(Log.TAG, "Album Request: $albumRequests")
                val adapter = TopAlbumListAdapter(
                    albumRequests = albumRequests,
                    listener = this@TopAlbumListFragment
                )
                bind.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    this.adapter = adapter
                    setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                    Log.d(Log.TAG, "TopAlbumList Size: ${albumRequests?.size}")
                }
                artistImage = albumRequests?.get(0)?.images?.get(3)?.text.toString()
                Glide.with(bind.imageView)
                    .load(artistImage)
                    .centerCrop()
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.icon_error)
                    .into(bind.imageView)
            }
        }
    }

    private fun setupLifeCycleObservers() {
    }

    override fun onItemClick(album: AlbumRequest) {
        Log.d(Log.TAG, "Album: ${album}")
        val action = MainFragmentDirections.toTracks(album)
        Log.d(Log.TAG, "Navi Action: $action")
        findNavController().navigate(action)
    }
}
