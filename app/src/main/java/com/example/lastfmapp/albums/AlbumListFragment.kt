package com.example.lastfmapp.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.databinding.FragmentAlbumListBinding
import com.example.lastfmapp.util.Log
import com.example.lastfmapp.util.Log.TAG
import com.example.lastfmapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private lateinit var _bind: FragmentAlbumListBinding
    private val bind get() = _bind
    private val albumListViewModel: AlbumListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentAlbumListBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupRecyclerView() {
//        bind.recyclerView.adapter =
//            AlbumListsAdapter(listOf(Album("one"), Album("two"), Album("three")))
    }

    private fun setupLifeCycleObservers() {
        with(albumListViewModel) {
            getAlbums().observe(viewLifecycleOwner) { topAlbums ->
                topAlbums?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            bind.albumListProgressBar.visibility = View.GONE
                            Log.d(TAG, "SUCCESS ${resource.data}")
//                             resource.data?.let { movieResponse -> updateUpComingMoviesList(movieResponse = movieResponse) }
                        }
                        Status.ERROR -> {
                            bind.albumListProgressBar.visibility = View.GONE
                            Log.d(TAG, "ERROR")
                            Toast.makeText(requireContext(), topAlbums.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            bind.albumListProgressBar.visibility = View.VISIBLE
                            Log.d(TAG, "LOADING")
                        }
                    }
                }
            }
        }
    }
}
