package ch.yvu.songbuzzer.http

import okhttp3.HttpUrl
import java.net.MalformedURLException

data class GetRequest(private val baseUrl: String,
                      private val path: String = "",
                      private val queryParameters: Map<String, String> = emptyMap()) {

    val url: String

    init {
        val urlBuilder = HttpUrl.parse(baseUrl)?.newBuilder() ?: throw MalformedURLException("Not a valid URL: $baseUrl")
        urlBuilder.addPathSegments(path)

        queryParameters.forEach { (key, value) -> urlBuilder.addQueryParameter(key, value) }

        url = urlBuilder.toString()
    }
}