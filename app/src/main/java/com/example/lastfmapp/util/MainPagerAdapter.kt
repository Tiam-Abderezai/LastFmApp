package com.example.lastfmapp.util
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lastfmapp.albums.AlbumsFragment
import com.example.lastfmapp.albums.ArtistsFragment

class MainPagerAdapter(activity: FragmentActivity, val fragments: List<Fragment>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount() = fragments.count()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment()
            1 -> ArtistsFragment()
            else -> {
                AlbumsFragment()
            }
        }
    }
}
