package ch.yvu.songbuzzer.http

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpClientOkHttp(private val okHttp: OkHttpClient) : HttpClient {
    override fun get(request: GetRequest): Single<Response> = Single.create { emitter ->
        val okHttpRequest = Request.Builder().get().url(request.url).build()
        val call = okHttp.newCall(okHttpRequest)
        emitter.setCancellable {
            call.cancel()
        }

        try {
            val response = call.execute()
            val body = response.body()?.string()
            emitter.onSuccess(Response(response.code(), body ?: ""))
        } catch (exception: IOException) {
            // Canceling the call leads to an Exception which we do not need to emit
            if (!call.isCanceled) {
                emitter.onError(exception)
            }
        }
    }
}