package com.example.lastfmapp.retrofit

import com.example.lastfmapp.albums.model.Album
import com.example.lastfmapp.albums.model.Artist
import com.example.lastfmapp.albums.model.Image
import com.example.lastfmapp.util.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class RetrofitRepositoryImplTest {
    private val mockWebServer = MockWebServer()
    val artist = Artist(
        mbid = "bfcc6d75-a6a5-4bc6-8282-47aec8531818",
        name = "Cher",
        url = "https://www.last.fm/music/Cher"
    )
    val imageSmall = Image(
        text = null,
        size = "small"
    )
    val imageMedium = Image(
        text = null,
        size = "medium"
    )
    val imageLarge = Image(
        text = null,
        size = "large"
    )
    val imageExtraLarge = Image(
        text = null,
        size = "extralarge"
    )

    val album = Album(
        artist = artist,
        listOf(imageSmall, imageMedium, imageLarge, imageExtraLarge),
        mbid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
        name = "Believe",
        url = "https://www.last.fm/music/Cher/Believe"
    )

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val retrofitService = Retrofit.Builder()
        .baseUrl(mockWebServer.url(Constants.BASE_URL))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitService::class.java)

    private val sut = RetrofitRepositoryImpl(retrofitService)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // TODO It's not good practice to test real endpoints,
    //  use FakeRepositoryImpl (in androidTest because it uses Hilt)
    //  with hardcoded JSON file in order to test this the right way
    @Test
    fun `should fetch albums correctly given 200 response`() {
        mockWebServer.enqueueResponse("topalbums_feed.json", 200)

        runBlocking {
            val expected = album
            val actual = sut.getTopAlbums().body()?.topAlbums?.albums?.get(0)

            assertEquals(
                expected,
                actual
            )
        }
    }
}

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val jsonFile =
        File("src\\test\\res\\$fileName").bufferedReader().use { it.readText() }.byteInputStream()
    val source = jsonFile.let { jsonFile.source().buffer() }
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(source.readString(StandardCharsets.UTF_8))
    )
}
