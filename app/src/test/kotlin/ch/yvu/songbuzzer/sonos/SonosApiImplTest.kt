package ch.yvu.songbuzzer.sonos

import ch.yvu.songbuzzer.http.HttpClient
import ch.yvu.songbuzzer.http.Response
import ch.yvu.songbuzzer.http.isGetRequest
import ch.yvu.songbuzzer.iz
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class SonosApiImplTest {

    companion object {
        private val sonosApiConfig = SonosApiConfig(baseUrl = "http://sonos-base")
    }

    private lateinit var httpClient: HttpClient

    private lateinit var sonosApi: SonosApi

    @Before
    fun setUp() {
        httpClient = mock {
            on { get(any()) } doReturn Single.just(Response(200, """{"items":[]}"""))
        }

        sonosApi = SonosApiImpl(httpClient, sonosApiConfig)
    }

    @Test
    fun sendsCorrectQueryToHttpClient() {
        val titleQuery = "SomeSong"
        sonosApi.findSongsByTitle(titleQuery).blockingGet()

        verify(httpClient).get(check {
            assertThat(it,
                    isGetRequest(
                            baseUrl = sonosApiConfig.baseUrl,
                            path = "search",
                            queryParameters = mapOf(
                                    "service" to "spotify",
                                    "type" to "song",
                                    "q" to titleQuery)
                    ))
        })
    }

    @Test
    fun parsesJsonCorrectly() {
        val title = "SomeSong"
        val artist = "Artist"
        val uri = "uri"
        val response = Response(200, """{
                    "items": [{"title": "$title", "artist": "$artist", "uri": "$uri"}]
                    }""")
        whenever(httpClient.get(any())).thenReturn(Single.just(response))

        val songListDto = sonosApi.findSongsByTitle("SomeSong").blockingGet()

        assertThat(songListDto, iz(SongListDto(listOf(SongDto(title, artist, uri)))))
    }

    @Test
    fun emitsErrorIfResponseIsNotSuccessful() {
        val response = Response(500, """{"items":[]}""")
        whenever(httpClient.get(any())).thenReturn(Single.just(response))

        val testObserver = sonosApi.findSongsByTitle("SomeSong").test()
        testObserver.await()

        assertThat(testObserver.errorCount(), iz(1))
    }

    @Test
    fun emitsErrorIfResponseCanNotBeParsed() {
        val response = Response(200, "invalid json")
        whenever(httpClient.get(any())).thenReturn(Single.just(response))

        val testObserver = sonosApi.findSongsByTitle("SomeSong").test()
        testObserver.await()

        assertThat(testObserver.errorCount(), iz(1))
    }
}