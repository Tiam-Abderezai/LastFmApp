package com.example.lastfmapp.data.model

import com.example.lastfmapp.main.albums.model.AlbumEntity
import com.example.lastfmapp.main.artists.model.ArtistEntity
import com.example.lastfmapp.util.ImageEntity

private val artistEntity = ArtistEntity(
    id = 1,
    mBid = "bfcc6d75-a6a5-4bc6-8282-47aec8531818",
    name = "Cher",
    url = "https://www.last.fm/music/Cher"
)

private val imageEntitySmall = ImageEntity(
    id = 1,
    text = null,
    size = "small"
)
private val imageEntityMedium = ImageEntity(
    id = 1,
    text = null,
    size = "medium"
)
private val imageEntityLarge = ImageEntity(
    id = 1,
    text = null,
    size = "large"
)
private val imageEntityExtraLarge = ImageEntity(
    id = 1,
    text = null,
    size = "extralarge"
)

internal val albumEntity1 = AlbumEntity(
    id = 1,
    artist = artistEntity,
    imageEntitySmall,
    mBid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
    name = "Believe",
    url = "https://www.last.fm/music/Cher/Believe"
)

internal val albumEntity2 = AlbumEntity(
    id = 2,
    artist = artistEntity,
    imageEntitySmall,
    mBid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
    name = "Believe",
    url = "https://www.last.fm/music/Cher/Believe"
)

internal val albumEntity3 = AlbumEntity(
    id = 3,
    artist = artistEntity,
    imageEntitySmall,
    mBid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
    name = "Believe",
    url = "https://www.last.fm/music/Cher/Believe"
)
