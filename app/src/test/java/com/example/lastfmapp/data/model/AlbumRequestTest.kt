package com.example.lastfmapp.data.model

import com.example.lastfmapp.albums.model.AlbumRequest
import com.example.lastfmapp.albums.model.ImageRequest
import com.example.lastfmapp.artists.model.ArtistRequest

private val artist = ArtistRequest(
    mBid = "bfcc6d75-a6a5-4bc6-8282-47aec8531818",
    name = "Cher",
    url = "https://www.last.fm/music/Cher"
)
private val imageSmall = ImageRequest(
    text = null,
    size = "small"
)
private val imageMedium = ImageRequest(
    text = null,
    size = "medium"
)
private val imageLarge = ImageRequest(
    text = null,
    size = "large"
)
private val imageExtraLarge = ImageRequest(
    text = null,
    size = "extralarge"
)

internal val album = AlbumRequest(
    artist = artist,
    listOf(imageSmall, imageMedium, imageLarge, imageExtraLarge),
    mBid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
    name = "Believe",
    url = "https://www.last.fm/music/Cher/Believe"
)