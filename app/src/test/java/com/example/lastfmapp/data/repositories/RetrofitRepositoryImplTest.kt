package com.example.lastfmapp.data.repositories

import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.data.model.album
import com.example.lastfmapp.data.model.artist
import com.example.lastfmapp.data.remote.RetrofitService
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

    @Test
    fun `should search artist correctly given 200 response`() {
        mockWebServer.enqueueResponse("search_artists_feed.json", 200)

        runBlocking {
            val expected = artist
            val actual = sut.searchArtists("Cher").body()?.results?.artists?.artists?.get(0)

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
