package com.example.lastfmapp.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    @GET("?method=track.getInfo&format=json")
//    fun retrieveTrackInfo(
//        @Query("artist") artist: String,
//        @Query("track") track: String,
//        @Query("api_key") apiKey: String
//    ): Call<LastFmApiTrackGetInfoResponse>

    @GET("?method=album.getInfo&format=json")
    fun getAlbumInfo(
        @Query("artist") artist: String?,
        @Query("album") album: String?,
        @Query("api_key") apiKey: String
    ): Call<ApiResponse>

    @GET("?method=artist.search&artist=cher&format=json")
    fun searchArtist(
        @Query("artist") artist: String?,
        @Query("album") album: String?,
        @Query("api_key") apiKey: String
    ): Call<ApiResponse>

//    @GET("?method=artist.getInfo&format=json")
//    fun getArtistInfo(
//        @Query("artist") artist: String?,
//        @Query("api_key") apiKey: String
//    ): Call<LastFmApiArtistGetInfoResponse>
//
//    @GET("?method=artist.gettopalbums&format=json&limit=15")
//    fun getArtistTopAlbumsInfo(
//        @Query("artist") artist: String?,
//        @Query("api_key") apiKey: String
//    ): Call<LastFmApiArtistTopAlbumsGetInfoResponse>
}
