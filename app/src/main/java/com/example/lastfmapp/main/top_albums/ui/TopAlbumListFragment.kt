package com.example.lastfmapp.main.top_albums.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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
        Toast.makeText(context, "${args.artistRequest.name}", Toast.LENGTH_SHORT).show()

        _bind = FragmentTopAlbumListBinding.bind(view)
        Log.d(Log.TAG, "TopAlbumList Artist: ${args.artistRequest.name}")
        bind.textViewCreator.text = args.artistRequest.name
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
        Log.d(Log.TAG, "")
        with(topAlbumsViewModel) {
            queryTopAlbums(args.artistRequest.name)
            topAlbumQueryLiveData.observe(viewLifecycleOwner) { albumRequest ->
                Log.d(Log.TAG, "Album Request: $albumRequest")
                val adapter = TopAlbumListAdapter(
                    topAlbums = albumRequest,
                    listener = this@TopAlbumListFragment
                )
                bind.recyclerView.setHasFixedSize(true)
                bind.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    this.adapter = adapter
                    setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                    Log.d(Log.TAG, "TopAlbumList Size: ${albumRequest?.size}")
                }
            }
        }
    }

    private fun setupLifeCycleObservers() {
    }

    override fun onItemClick(album: AlbumRequest) {
        Log.d(Log.TAG, "Album: ${album.name}")
        val action = MainFragmentDirections.toTracks(album)
        Log.d(Log.TAG, "Navi Action: $action")
        findNavController().navigate(action)
        Toast.makeText(context, "${album.name}", Toast.LENGTH_SHORT).show()
    }
}
