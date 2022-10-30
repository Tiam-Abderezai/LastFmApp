package com.example.lastfmapp.main.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lastfmapp.databinding.FragmentMainBinding
import com.example.lastfmapp.main.albums.ui.list.AlbumListFragment
import com.example.lastfmapp.main.artists.ui.ArtistListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var _bind: FragmentMainBinding
    private val bind get() = _bind

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = FragmentMainBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf(
            AlbumListFragment(),
            ArtistListFragment()
        )

        val adapter = MainPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        bind.viewPager.adapter = adapter
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainFragmentViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        with(mainFragmentViewModel) {
            toTopAlbumFragLiveData.observe(viewLifecycleOwner) {
                val action = MainFragmentDirections.toTopAlbums(it)
                findNavController().navigate(action)
            }
            toTracksFragLiveData.observe(viewLifecycleOwner) {
                val action = MainFragmentDirections.toTracks(it)
                findNavController().navigate(action)
            }
            toAlbumDetailFragLiveData.observe(viewLifecycleOwner) {
                val action = MainFragmentDirections.toAlbumDetail(it)
                findNavController().navigate(action)
            }
        }
    }
}
