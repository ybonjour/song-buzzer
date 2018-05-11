package ch.yvu.songbuzzer.http

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol.HTTP_2
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class HttpClientOkHttpTest {
    companion object {
        private val url = "http://example.com"
    }

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var httpClient: HttpClient

    @Before
    fun setUp() {
        okHttpClient = mock()
        httpClient = HttpClientOkHttp(okHttpClient)
    }

    @Test
    fun getReturnsBodyOfResponse() {
        val body = "body"
        val response = response(body = body)
        givenResponse(url, response)

        val result = httpClient.get(url).blockingGet()

        assertThat(result).isEqualTo(body)
    }

    @Test
    fun getEmitsErrorIfResponseIsNotSuccessful() {
        val response = response(statusCode = 500, statusMessage = "INTERNAL SERVER ERROR")
        givenResponse(url, response)

        val testObserver = httpClient.get(url).test()
        testObserver.await()

        testObserver.assertError(HttpException::class.java)
    }

    @Test
    fun getReturnsEmptyStringIfBodyIsNull() {
        val response = response(body = null)
        givenResponse(url, response)

        val result = httpClient.get(url).blockingGet()

        assertThat(result).isEmpty()
    }

    @Test
    fun getDoesNotEmitErrorIfCallIsAlreadyCanceled() {
        val call: Call = mock { on { isCanceled } doReturn true }
        val latch = CountDownLatch(1)
        whenever(okHttpClient.newCall(any())).thenReturn(call)
        whenever(call.execute()).thenAnswer {
            latch.countDown()
            throw Exception()
        }

        val testObserver = httpClient.get(url).test()
        latch.await(1, SECONDS)

        testObserver.assertNoErrors()
    }

    private fun givenResponse(url: String, response: Response) {
        val call = call(response)
        whenever(okHttpClient.newCall(argThat {
            this.url() == HttpUrl.parse(url)
        })).thenReturn(call)
    }

    private fun call(response: Response): Call =
        mock { on { execute() } doReturn response }

    private fun response(body: String? = null, statusCode: Int = 200, statusMessage: String = "OK"): Response {
        val responseBody = if (body == null) null else ResponseBody.create(MediaType.parse("text/json"), body)
        return Response.Builder()
            .body(responseBody)
            .code(statusCode)
            .message(statusMessage)
            .protocol(HTTP_2)
            .request(Request.Builder().url(url).build())
            .build()
    }
}