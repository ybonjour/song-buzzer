package ch.yvu.songbuzzer.http

import dagger.Module
import okhttp3.OkHttpClient

@Module
class HttpModule {
    fun okHttpClient(): OkHttpClient = OkHttpClient()
    fun httpClient(okHttpClient: OkHttpClient): HttpClient = HttpClientOkHttp(okHttpClient)
}