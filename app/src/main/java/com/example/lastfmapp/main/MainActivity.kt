package com.example.lastfmapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.lastfmapp.R
import com.example.lastfmapp.albums.AlbumListFragment
import com.example.lastfmapp.albums.ArtistListFragment
import com.example.lastfmapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val fragments = listOf(AlbumListFragment(), ArtistListFragment())
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bind: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    lateinit var viewPager: ViewPager2
    private lateinit var mainPageAdapter: MainPagerAdapter
    private val mOnNavigationItemSelectedListener = NavigationBarView.OnItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.menu_item_top_albums -> {
                viewPager.currentItem = 0
                return@OnItemSelectedListener true
            }
            R.id.menu_item_search_artist -> {
                viewPager.currentItem = 1
                return@OnItemSelectedListener true
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbarMain)
        setupNavigation()
        setupLifeCycleObservers()
    }

    private fun setupLifeCycleObservers() {
//        with(mainViewModel)  {
//            main
//        }
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        viewPager = bind.viewPager
        mainPageAdapter = MainPagerAdapter(this, fragments)
        bottomNav = bind.bottomNav
        bottomNav.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.apply {
            adapter = mainPageAdapter
            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            bottomNav.menu.findItem(R.id.menu_item_top_albums).isChecked = true
                        }
                        1 -> {
                            bottomNav.menu.findItem(R.id.menu_item_search_artist).isChecked = true
                        }
                    }
                }
            })
        }
    }
}
