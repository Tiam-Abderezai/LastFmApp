package com.example.lastfmapp.data.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.lastfmapp.data.local.AlbumDao
import com.example.lastfmapp.data.local.RoomDB
import com.example.lastfmapp.data.model.albumEntity1
import com.example.lastfmapp.data.model.albumEntity2
import com.example.lastfmapp.data.model.albumEntity3
import com.example.lastfmapp.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RoomDB
    private lateinit var albumDao: AlbumDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomDB::class.java
        ).allowMainThreadQueries().build()
        albumDao = database.albumDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAlbum() = runTest {
        albumDao.insertAlbum(albumEntity1)
        albumDao.insertAlbum(albumEntity2)
        albumDao.insertAlbum(albumEntity3)

        val allAlbums = albumDao.observeAllAlbums().getOrAwaitValue()

        assertThat(allAlbums).contains(albumEntity1)
    }

    @Test
    fun deleteAlbum() = runTest {
        albumDao.insertAlbum(albumEntity1)
        albumDao.deleteAlbum(albumEntity1)

        val allAlbums = albumDao.observeAllAlbums().getOrAwaitValue()

        assertThat(allAlbums).doesNotContain(albumEntity1)
    }
}
