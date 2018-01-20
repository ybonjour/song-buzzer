package ch.yvu.songbuzzer.http

import java.net.URL

data class GetRequest(private val url: URL, private val queryParameters: Map<String, String>)