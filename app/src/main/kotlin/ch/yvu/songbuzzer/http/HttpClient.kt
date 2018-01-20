package ch.yvu.songbuzzer.http

interface HttpClient {
    fun get(request: GetRequest): Response
}