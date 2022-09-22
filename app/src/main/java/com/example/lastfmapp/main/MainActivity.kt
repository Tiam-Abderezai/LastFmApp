package com.example.lastfmapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lastfmapp.R
import com.example.lastfmapp.albums.AlbumListFragment
import com.example.lastfmapp.albums.ArtistListFragment
import com.example.lastfmapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fragments = listOf(AlbumListFragment(), ArtistListFragment())
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bind: ActivityMainBinding
    private val mainPageAdapter by lazy {
        MainPagerAdapter(this, fragments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupViewPager()
    }

    private fun setupUI() {
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupViewPager() {
        bind.viewPager.adapter = mainPageAdapter
    }
}
