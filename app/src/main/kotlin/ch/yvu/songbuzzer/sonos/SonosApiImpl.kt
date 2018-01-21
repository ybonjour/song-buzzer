package ch.yvu.songbuzzer.sonos

import ch.yvu.songbuzzer.http.GetRequest
import ch.yvu.songbuzzer.http.HttpClient
import com.beust.klaxon.Klaxon
import io.reactivex.Single

class SonosApiImpl(
        private val httpClient: HttpClient,
        private val sonosApiConfig: SonosApiConfig) : SonosApi {

    override fun findSongsByTitle(titleQuery: String) = Single.defer<SongListDto> {
        val request = GetRequest(sonosApiConfig.baseUrl, "search",
                mapOf(
                        "service" to "spotify",
                        "type" to "song",
                        "q" to titleQuery
                ))

        httpClient.get(request)
                .map {
                    if(!it.isSuccessful) {
                        throw Exception("Unsuccessful response")
                    }
                    Klaxon().parse<SongListDto>(it.body) }
    }
}