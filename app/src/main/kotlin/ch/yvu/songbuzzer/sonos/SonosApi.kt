package ch.yvu.songbuzzer.sonos

import io.reactivex.Single

interface SonosApi {
    fun findSongsByTitle(titleQuery: String): Single<List<SongDto>>
}
