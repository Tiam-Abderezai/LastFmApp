package com.example.lastfmapp.main.albums.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfmapp.databinding.FragmentAlbumListBinding
import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.main.MainFragmentDirections
import com.example.lastfmapp.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment(), AlbumListAdapter.OnItemClickListener {

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
    }

    private fun setupRecyclerView() {
        with(albumListViewModel) {
            albumListLiveData.observe(viewLifecycleOwner) { albumEntity ->
                Log.d(Log.TAG, "albumEntity: $albumEntity")
                if (albumEntity != null) {
                    val adapter = AlbumListAdapter(
                        albumEntity,
                        this@AlbumListFragment
                    )
                    bind.recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        this.adapter = adapter
                        setHasFixedSize(true)
                        adapter.notifyDataSetChanged()
                        Log.d(Log.TAG, "AlbumList Size: ${albumEntity?.size}")
                    }
                }
            }
        }
    }

    override fun onItemClick(album: AlbumEntity) {
        val action = MainFragmentDirections.toAlbumDetail(album)
        findNavController().navigate(action)
    }
}
