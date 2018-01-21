package ch.yvu.songbuzzer.http

import okhttp3.HttpUrl

data class GetRequest(private val baseUrl: String, private val path: String = "", val queryParameters: Map<String, String> = emptyMap()) {

    val url: String
        get() {
            val urlBuilder = HttpUrl.parse(baseUrl)
                    ?.newBuilder()
                    ?.addPathSegments(path)

            queryParameters.forEach { (key, value) -> urlBuilder?.addQueryParameter(key, value) }

            return urlBuilder?.toString() ?: ""
        }

}