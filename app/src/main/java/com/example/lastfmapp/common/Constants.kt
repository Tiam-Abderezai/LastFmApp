package com.example.lastfmapp.util

import com.example.lastfmapp.BuildConfig

class Constants {
    companion object {
        // Retrofit Constants
        const val API_KEY = BuildConfig.API_KEY
        const val API_SECRET = BuildConfig.API_SECRET
        const val BASE_URL = BuildConfig.BASE_URL
        const val SEARCH_NEWS_TIME_DELAY = 500L

        // Room Constants
        const val DATABASE_NAME = "last_fm_db"
        const val TABLE_ALBUM = "table_album"
        const val TABLE_ALBUMS = "table_albums"
        const val TABLE_ATTR = "table_attr"
        const val TABLE_IMAGE = "table_image"
        const val TABLE_TAG = "table_tag"
        const val TABLE_ARTIST = "table_artist"
        const val TABLE_TRACKS = "table_tracks"

        // Paging
        const val ARTIST_STARTING_PAGE_INDEX = 1
        const val ALBUMS_STARTING_PAGE_INDEX = 1
    }
}
