package ch.yvu.songbuzzer.http

import ch.yvu.songbuzzer.iz
import org.junit.Assert.assertThat
import org.junit.Test

class GetRequestTest {
    @Test
    fun urlReturnsBaseUrl() {
        val baseUrl = "http://yvu.ch/"

        val request = GetRequest(baseUrl)

        assertThat(request.url, iz(baseUrl))
    }

    @Test
    fun urlReturnsUrlWithPath() {
        val baseUrl = "http://yvu.ch/"
        val path = "/foo/bar"

        val request = GetRequest(baseUrl, path)

        assertThat(request.url, iz("$baseUrl$path"))
    }

    @Test
    fun urlReturnsUrlWithSingleQueryParameter() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key" to "value")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key=value"))
    }

    @Test
    fun urlReturnsUrlWithMultipleQueryParameters() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key1" to "value1", "key2" to "value2")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key1=value1&key2=value2"))
    }

    @Test
    fun urlReturnsEncodedParameters() {
        val baseUrl = "http://yvu.ch"
        val queryParams = mapOf("key 1" to "value 1")

        val request = GetRequest(baseUrl, queryParameters = queryParams)

        assertThat(request.url, iz("$baseUrl/?key%201=value%201"))
    }
}