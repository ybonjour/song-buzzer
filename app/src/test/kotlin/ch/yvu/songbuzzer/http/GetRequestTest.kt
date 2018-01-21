package ch.yvu.songbuzzer.http

import ch.yvu.songbuzzer.iz
import org.junit.Assert.assertThat
import org.junit.Test
import java.net.MalformedURLException

class GetRequestTest {
    @Test
    fun buildsUrlWithBaseUrl() {
        val baseUrl = "http://yvu.ch/"

        val request = GetRequest(baseUrl)

        assertThat(request.url, iz(baseUrl))
    }

    @Test
    fun withPath() {
        val baseUrl = "http://yvu.ch/"
        val path = "/foo/bar"

        val request = GetRequest(baseUrl, path)

        assertThat(request.url, iz("$baseUrl$path"))
    }

    @Test
    fun encodesPath() {
        val baseUrl = "http://yvu.ch"
        val path = "some path"

        val request = GetRequest(baseUrl, path)

        assertThat(request.url, iz("$baseUrl/some%20path"))
    }

    @Test
    fun buildsUrlWithSingleQueryParameter() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key" to "value")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key=value"))
    }

    @Test
    fun buildsUrlWithMultipleQueryParameters() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key1" to "value1", "key2" to "value2")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key1=value1&key2=value2"))
    }

    @Test
    fun encodedParameters() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key 1" to "value 1")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key%201=value%201"))
    }

    @Test(expected = MalformedURLException::class)
    fun throwsExceptionIfBaseUrlIsInvalid() {
        val baseUrl = "invalid"

        GetRequest(baseUrl)
    }
}