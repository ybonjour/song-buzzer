package ch.yvu.songbuzzer.http

import io.reactivex.Single
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HttpClientOkHttp(
    private val httpClient: OkHttpClient
) : HttpClient {
    override fun get(url: String) =
        Single.create<String> { emitter ->
            var call: Call? = null
            try {
                val request = Request.Builder().url(url).build()
                call = httpClient.newCall(request)
                emitter.setCancellable {
                    call?.cancel()
                }

                val response = call.execute()

                if (!response.isSuccessful) {
                    throw HttpException(response)
                }

                emitter.onSuccess(extractBody(response))
            } catch (exception: Exception) {
                val ignoreError = call != null && !call.isCanceled
                if (!ignoreError) {
                    emitter.onError(exception)
                }
            }
        }

    private fun extractBody(httpResponse: Response): String {
        httpResponse.use { response -> return response.body()?.string() ?: "" }
    }

}