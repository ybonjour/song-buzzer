package ch.yvu.songbuzzer.http

import io.reactivex.Single

interface HttpClient {
    fun get(url: String): Single<String>
}