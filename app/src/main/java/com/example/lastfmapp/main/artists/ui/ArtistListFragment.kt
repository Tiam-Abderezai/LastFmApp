package com.example.lastfmapp.main.artists.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.R
import com.example.lastfmapp.databinding.FragmentArtistListBinding
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.example.lastfmapp.main.artists.ui.page.ArtistLoadStateAdapter
import com.example.lastfmapp.main.main.MainFragmentDirections
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistListFragment : Fragment(), ArtistListAdapter.OnItemClickListener {

    private lateinit var _bind: FragmentArtistListBinding
    private val bind get() = _bind
    private val artistListViewModel by lazy { ViewModelProvider(this)[ArtistListViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentArtistListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        setupRecyclerView()
        setupLifeCycleObservers()
    }

    private fun setupSearch() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menu.clear()
                    menuInflater.inflate(R.menu.menu_artists, menu)
                    val searchItem = menu.findItem(R.id.action_search)
                    val searchView = searchItem.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            if (query != null) {
                                bind.recyclerView.scrollToPosition(0)
                                artistListViewModel.queryArtist(query)
                                searchView.clearFocus()
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            return true
                        }
                    })
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    println("ITEMID ${menuItem.itemId}")

                    // Handle the menu selection
                    return when (menuItem.itemId) {
                        R.id.menu_item_top_albums -> {
                            // clearCompletedTasks()
                            true
                        }
                        R.id.menu_item_search_artist -> {
                            println("ITEMID ${menuItem.itemId}")
                            // loadTasks(true)
                            true
                        }
                        else -> false
                    }
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    private fun setupRecyclerView() {
        val adapter = ArtistListAdapter(this)
        bind.recyclerView.setHasFixedSize(true)
        bind.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ArtistLoadStateAdapter { adapter.retry() },
            footer = ArtistLoadStateAdapter { adapter.retry() }
        )
        artistListViewModel.queryLiveData.observe(viewLifecycleOwner) { artistRequest ->
            Log.d(Log.TAG, "SEARCH ARTIST: $artistRequest")
            adapter.submitData(viewLifecycleOwner.lifecycle, artistRequest)
        }
        // 'setHasOptionsMenu(Boolean): Unit' is deprecated. Deprecated in Java
        setHasOptionsMenu(true)
    }

    private fun setupLifeCycleObservers() {
        with(artistListViewModel) {
            artistListLiveData.observe(viewLifecycleOwner) {
            }
        }
    }

    override fun onItemClick(artist: ArtistRequest) {
        val action = MainFragmentDirections.toTopAlbums(artist)
        findNavController().navigate(action)
    }
}
