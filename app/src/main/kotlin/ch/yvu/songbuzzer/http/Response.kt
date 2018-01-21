package ch.yvu.songbuzzer.http

data class Response(private val statusCode: Int, val body: String = "") {
    val isSuccessful = statusCode in 200..299
}