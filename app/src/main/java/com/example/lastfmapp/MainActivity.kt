package com.example.lastfmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lastfmapp.albums.AlbumsFragment
import com.example.lastfmapp.albums.ArtistsFragment
import com.example.lastfmapp.databinding.ActivityMainBinding
import com.example.lastfmapp.util.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private val fragments = listOf(AlbumsFragment(), ArtistsFragment())
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

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupViewPager() {
        bind.viewPager.adapter = mainPageAdapter
    }
}

