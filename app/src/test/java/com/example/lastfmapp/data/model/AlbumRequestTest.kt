package com.example.lastfmapp.data.model

import com.example.lastfmapp.main.albums.model.AlbumRequest
import com.example.lastfmapp.main.artists.model.ArtistRequest
import com.example.lastfmapp.util.ImageRequest

private val imageSmall = ImageRequest(
    text = "https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png",
    size = "small"
)
private val imageMedium = ImageRequest(
    text = "https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png",
    size = "medium"
)
private val imageLarge = ImageRequest(
    text = "https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png",
    size = "large"
)
private val imageExtraLarge = ImageRequest(
    text = "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
    size = "extralarge"
)
private val imageMega = ImageRequest(
    text = "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
    size = "mega"
)

internal val artist = ArtistRequest(
    mBid = "bfcc6d75-a6a5-4bc6-8282-47aec8531818",
    name = "Cher",
    listeners = 1421015,
    url = "https://www.last.fm/music/Cher",
    images = listOf(imageSmall, imageMedium, imageLarge, imageExtraLarge, imageMega)
)

internal val album = AlbumRequest(
    artist = artist,
    listOf(imageSmall, imageMedium, imageLarge, imageExtraLarge),
    mBid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
    name = "Believe",
    url = "https://www.last.fm/music/Cher/Believe"
)
